package com.dduckdori.SsdamServer.Login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
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
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
    //@Value("${apple.authURL}")
    private final static String APPLE_AUTH_URL="https://appleid.apple.com";

    @Override
    public String getRedirectURL() {
        return APPLE_AUTH_URL + "/auth/authorize"
                + "?client_id=" + APPLE_CLIENT_ID
                + "&redirect_uri=" + APPLE_REDIRECT_URL
                + "&response_type=code%20id_token&scope=name%20email&response_mode=form_post";
    }

    @Override
    public String getToken(AppleDTO appleDTO) throws ParseException, JsonProcessingException, JOSEException {

        SignedJWT signedJWT = SignedJWT.parse(appleDTO.getId_token());
        JWTClaimsSet payload = signedJWT.getJWTClaimsSet();
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
            System.out.println("signature = " + signature);
        }
        //Verify the JWS E256 signature using the server’s public key
        Date currentTime = new Date(System.currentTimeMillis());
        String aud = payload.getAudience().get(0);
        String iss = payload.getIssuer();
        String nonce = (String) payload.getClaim("nonce");
        if(!currentTime.before(payload.getExpirationTime()) || !aud.equals(APPLE_TEAM_ID)
                ||!iss.equals("https://appleid.apple.com")
        ){
            //실패처리
        }

        return "";
    }

    @Override
    public String authToken(AppleDTO appleDTO) throws IOException, net.minidev.json.parser.ParseException, ParseException {

        ClassPathResource resource = new ClassPathResource(APPLE_KEY_PATH);
        InputStream inputStream = resource.getInputStream();
        PEMParser pemParser = new PEMParser(new StringReader(IOUtils.toString(inputStream, StandardCharsets.UTF_8)));
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
        Date expirationDate = Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant());
        String clientSecret = Jwts.builder()
                .setHeaderParam("kid","A43DNX9QCY")
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

        //String response  = HttpClientUtils.doPost("https://appleid.apple.com/auth/token",tokenRequest);
        String response = "{\"access_token\":\"a5c80edcde065487486de3deed1a0e515.0.rrxww.xcJJcEu5lh97LrEx_D-e1Q\",\"token_type\":\"Bearer\",\"expires_in\":3600,\"refresh_toke" +
                "n\":\"r58a936c329ef490aa08ffcfea136f8e9.0.rrxww.UWi3In6ZEgwNx2ciiexAMQ\",\"id_token\":\"eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJod" +
                "HRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRkdWNrZG9yaS5Tc2RhbVNlcnZlciIsImV4cCI6MTcwMzE3MjczMCwiaWF0IjoxNzAzMDg2MzMwLCJzdWIiOiIwM" +
                "DE3NjYuMTg3OTVkNzE4NDU0NGM2ZWJjMWFhZGJhYTc4NWM4OGUuMTAzMiIsImF0X2hhc2giOiI3V0Z5RmU0Q1lIYTdJaGI5ZWtxNzhnIiwiZW1haWwiOiJsZGhkZXZlbG9wQGdtY" +
                "WlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTcwMzA4NjMyNywibm9uY2Vfc3VwcG9ydGVkIjp0cnVlfQ.WbN-g62jFJLNws9tu_DvOqOu_HYufE" +
                "huwioXPErbN1YmnhzmoCJG-twLU9ncs_A2NN6PVZ5cUuxlBnV_U0QTShH0FIXbpA3TY1hs0pmCkPHEPQP69ySzUEULl7PyFjJC4H2iWifeCqscuS1H3LTh5U0rvvtiwcOcsqQUtz" +
                "Ug5vZuJKjKlhvr_1CdSr8Uzimjg8-xNS4Ruml16qpelxL9Q-kTFisTZfy0kjZbsR1cQlNX-XfRFH9Nlpl6EvFpmSL85QXby5fuvZ9SvS3DThiZtZX5A7NRR87vRVCAxWpEqWHq_3" +
                "GA4N9UuicHUsaEUfhPp68e1JHm8iDm-YwpS6X8pg\"}";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(response);
        JSONObject jsonObject = (JSONObject) obj;
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setAcces_token((String) jsonObject.get("access_token"));
        responseDTO.setToken_type((String) jsonObject.get("token_type"));
        responseDTO.setExpires_in((Integer) jsonObject.get("expires_in"));
        responseDTO.setRefresh_token((String) jsonObject.get("refresh_token"));
        responseDTO.setId_token((String) jsonObject.get("id_token"));
        System.out.println("responseDTO = " + responseDTO.getId_token());
        SignedJWT signedJWT = SignedJWT.parse((String) jsonObject.get("id_token"));
        JWTClaimsSet payload = signedJWT.getJWTClaimsSet();
        System.out.println("payload = " + payload);//email 빼내고
        //Base64.Decoder decoder = Base64.getDecoder();
        //byte[] decodedBytes = decoder.decode(responseDTO.getId_token());
        //System.out.println("new String(decodedBytes) = " + new String(decodedBytes));
//        response.
//        System.out.println(response);
//        String[] arr_response = response.split(",");
//        for(String arr : arr_response){
//            System.out.println(arr);
//        }
//        ResponseDTO responseDTO= null;
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER,true);
//        mapper.writeValue(new File(response),responseDTO);
//        System.out.println("responseDTO = " + responseDTO);
//        //System.out.println("responseDTO = " + responseDTO);
        return null;
    }
}
/*
response =
{
"access_token":"a4b5f7f93bac24b018935559439e140e6.0.rrxww.yKSo88EnFGLsC_2TDKQXwA",
"token_type":"Bearer",
"expires_in":3600,
"refresh_token":"r4ccf873bead74fa28f73d9594fa19631.0.rrxww.eTGTQhFkUHcz1zUzgvhN-A",
"id_token":"eyJraWQiOiJXNldjT0tCIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRkdWNrZG9yaS5Tc2RhbVNlcnZlciIsImV4cCI6MTcwMzE3MTI5OCwiaWF0IjoxNzAzMDg0ODk4
LCJzdWIiOiIwMDE3NjYuMTg3OTVkNzE4NDU0NGM2ZWJjMWFhZGJhYTc4NWM4OGUuMTAzMiIsImF0X2hhc2giOiJIV2dsdklUSFdJaHFmY3A2TkJKOHZBIiwiZW1haWwiOiJsZGhk
ZXZlbG9wQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTcwMzA4NDg5NSwibm9uY2Vfc3VwcG9ydGVkIjp0cnVlfQ.eT5DnHgIhmDeSKCzS
ufBy51j-3kb0LmZMgl-avgsGrroPdixmeq8G3UZZFUVpN9EK7taTeSqOmjfpIz7BWJ3PcY7Q4j2-cMj3oeqIAhfEBAVAR3wy3e_UyuuAxldH6zeei_n5U4nl-gitH58tX3i9yAd5
EQWT3JoO3GaZUsE7F6ZmEIsecQwVaTZErxmJV-b-E2ZANdg2DOwroXa0iUzJHc3_wQDDfqqwj04p7-z1uh0oAeixN42fRPKtZ8zY40X25eBE0kof92a7uQLVa4fhkBUfK-FwvwsK
PeZ-Fc_P4A7g2akK024BvUMzZ494uws_2ipnzFl8J37iNSRo8lZ1w"}
 */