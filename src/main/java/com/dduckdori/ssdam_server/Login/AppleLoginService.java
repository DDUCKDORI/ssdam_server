package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Exception.TryAgainException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;


import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class AppleLoginService implements LoginService {
    @Value("${apple.clientId}")
    private String APPLE_CLIENT_ID;
    @Value("${apple.redirectUri}")
    private String APPLE_REDIRECT_URL;
    @Value("${apple.clientKey}")
    private String APPLE_LOGIN_KEY;
    @Value("${apple.teamId}")
    private String APPLE_TEAM_ID;
    @Value("${apple.clientSecret}")
    private String APPLE_KEY_PATH;

    private final static String APPLE_AUTH_URL="https://appleid.apple.com";
    private final LoginRepository loginRepository;

    @Override
    public String getRedirectURL() {
        return APPLE_AUTH_URL + "/auth/authorize"
                + "?client_id=" + APPLE_CLIENT_ID
                + "&redirect_uri=" + APPLE_REDIRECT_URL
                + "&response_type=code%20id_token&scope=name%20email&response_mode=form_post";
    }

    @Override
    public LoginDTO getToken(AppleDTO appleDTO) throws ParseException, JsonProcessingException, JOSEException {

        SignedJWT signedJWT = SignedJWT.parse(appleDTO.getId_token());
        JWTClaimsSet payload = signedJWT.getJWTClaimsSet();

        //여기서 sub 값으로 데이터베이스에 해당 sub 값이 있는지 판단
        appleDTO.setSub((String)payload.getClaims().get("sub"));
//        LoginDTO loginDTO = loginRepository.find_sub(appleDTO);
//        if(loginDTO!=null){
//            //데이터베이스에 sub값이 있다면 널이아님.
//            //refresh_token, 유저정보, sub 값을 loginDTO 담아 반환.
//            //없다면
//            //null이므로 다음 진행.
//            return loginDTO;
//        }

        String publicKeys = HttpClientUtils.doGet("https://appleid.apple.com/auth/keys");
        ObjectMapper objectMapper = new ObjectMapper();
        Keys keys = objectMapper.readValue(publicKeys,Keys.class);
        boolean signature = false;
        for(Key key : keys.getKeys()){
            RSAKey rsaKey = (RSAKey) JWK.parse(objectMapper.writeValueAsString(key));
            RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
            JWSVerifier verifier = new RSASSAVerifier(publicKey);
            if(signedJWT.verify(verifier)){
                appleDTO.setKid(signedJWT.getHeader().getKeyID());
                signature=true;
            }
        }

        //공개키를 통해 header와 payload 의 값이 같은지 비교
        if(signature == false){
            // TODO: 12/22/23
            //실패처리 -> 예외처리
        }
        //Verify the JWS E256 signature using the server’s public key
        Date currentTime = new Date(System.currentTimeMillis());
        String aud = payload.getAudience().get(0);
        String iss = payload.getIssuer();
        String nonce = (String) payload.getClaim("nonce");
        if(!currentTime.before(payload.getExpirationTime()) || !aud.equals(APPLE_TEAM_ID) ||!iss.equals("https://appleid.apple.com")){
            // TODO: 12/22/23
            //실패처리 -> 예외처리
        }
        return null;
    }

    @Override
    public LoginDTO authToken(AppleDTO appleDTO) throws IOException, net.minidev.json.parser.ParseException, ParseException {

        ClassPathResource resource = new ClassPathResource(APPLE_KEY_PATH);
        InputStream inputStream = resource.getInputStream();
        PEMParser pemParser = new PEMParser(new StringReader(IOUtils.toString(inputStream, StandardCharsets.UTF_8)));
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
        Date expirationDate = Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant());
        //clientSecret 발급
        String clientSecret = Jwts.builder()
                .setHeaderParam("kid",APPLE_LOGIN_KEY)
                .setHeaderParam("alg","ES256")
                .setIssuer(APPLE_TEAM_ID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .setAudience("https://appleid.apple.com")
                .setSubject(APPLE_CLIENT_ID)
                .signWith(SignatureAlgorithm.ES256,converter.getPrivateKey(object))
                .compact();

        Map<String,String> tokenRequest = new HashMap<>();
        tokenRequest.put("client_id", APPLE_CLIENT_ID);
        tokenRequest.put("client_secret", clientSecret);
        tokenRequest.put("code", appleDTO.getCode());
        tokenRequest.put("grant_type", "authorization_code");
        tokenRequest.put("redirect_uri", "https://test-ssdam.site/ssdam/apple/login/callback");

        String response  = HttpClientUtils.doPost("https://appleid.apple.com/auth/token",tokenRequest);

        //String -> DTO
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(response);
        JSONObject jsonObject = (JSONObject) obj;
        LoginDTO loginDTO= new LoginDTO();
        loginDTO.setAccess_token((String) jsonObject.get("access_token"));
        loginDTO.setRefresh_token((String) jsonObject.get("refresh_token"));

        //id_token으로 email 추출
        SignedJWT signedJWT = SignedJWT.parse((String) jsonObject.get("id_token"));
        JWTClaimsSet payload = signedJWT.getJWTClaimsSet();

        loginDTO.setEmail((String) payload.getClaims().get("email"));
        loginDTO.setMem_sub((String) payload.getClaims().get("sub"));

        return loginDTO;
    }

    @Override
    public ResponseDTO joinMember(LoginDTO loginDTO) {
        //초대코드 여부 판별
        if(loginDTO.getInvite_cd()==null){
            //8자리 초대코드 만들기
            loginDTO.setInvite_cd(make_InviteCd());
        }
        //초대코드 존재한다면 데이터베이스에 회원 정보 저장.
        int result = loginRepository.join_member(loginDTO);
        if(result != 1){
            throw new TryAgainException("잠시 후 다시 시도해주시기 바랍니다.");
        }
        result = loginRepository.join_member_token(loginDTO);
        if(result!=1){
            throw new TryAgainException("잠시 후 다시 시도해주시기 바랍니다.");
        }
        ResponseDTO responseDTO = loginRepository.find_mem_info(loginDTO);
        responseDTO.setAccess_token(loginDTO.getAccess_token());
        responseDTO.setRefresh_token(loginDTO.getRefresh_token());
        return responseDTO;
    }

    private String make_InviteCd() {
        StringBuilder stringBuilder = new StringBuilder();
        Random rd = new Random();
        for(int i=0;i<4;i++){
            stringBuilder.append((char)(rd.nextInt(26)+65));
        }
        for(int i=0;i<4;i++){
            stringBuilder.append(rd.nextInt(10));
        }
        return stringBuilder.toString();
    }
}