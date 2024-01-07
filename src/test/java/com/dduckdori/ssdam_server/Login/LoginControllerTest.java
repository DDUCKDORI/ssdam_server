package com.dduckdori.ssdam_server.Login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@SpringBootTest
class LoginControllerTest {
    @Autowired
    private LoginService loginService;

    @Test
    void getToekn() throws Exception{
        AppleDTO appleDTO = new AppleDTO("ca670400dba8440f8b283117189433414.0.rrxww.KlwNiDA7eyXzIYAPrBIj3Q",
                "eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRkdWNrZG9yaS5Tc2RhbVNlcnZlciIsImV4cCI6MTcwMjkxNzQ3NSwiaWF0IjoxNzAyODMxMDc1LCJzdWIiOiIwMDE3NjYuMTg3OTVkNzE4NDU0NGM2ZWJjMWFhZGJhYTc4NWM4OGUuMTAzMiIsImNfaGFzaCI6ImVzd09vWjI5aVBtRG9TeXZXRUdlNVEiLCJlbWFpbCI6ImxkaGRldmVsb3BAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNzAyODMxMDc1LCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.J5oOn5W1NOx599myF3AesuUlBg80mUedGNrUie1Xf6uSIdBNHoXWAylZd3P1eWmnfcGVPYCuvFVeT0ITrSKXLSE6KyAilza9UJOj2aL6EHU4WvDhL1no_HyjZiyXmFwnx8_Mkzc0gCV2k-0q5-COO161WCL690y5eyTYrudIBQO7_iRDRGuuxBaidJ49CTjCbRa68h6zyHvv26I0ziZ6tkim2Y-33vCRGuguh7NklmL4-nyMJlFcwsa4iBeizEUzIPGzYPHnMuMGHdBKWCdCal8hbZNp_kjQV4sCzN9FOQItISrAPigcnM3RjTXGT4u_gscPN0QE2mnFpVLbMhgILQ");
        //code: authorization code
        //id_token : 애플에서 보낸 jwt
        //System.out.println("source = " + source);
        String[] jwt = appleDTO.getId_token().split("[.]");
        Base64.Decoder decoder= Base64.getDecoder();
//        for(String info:jwt){
//            byte[] decodedBytes = decoder.decode(info);
//            System.out.println("인코딩 전: " + new String(decodedBytes));
//        }
     //   loginService.getToken(appleDTO);
    }
    @Test
    void authToken() throws Exception{
        AppleDTO appleDTO = new AppleDTO("ca670400dba8440f8b283117189433414.0.rrxww.KlwNiDA7eyXzIYAPrBIj3Q",
                "eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRkdWNrZG9yaS5Tc2RhbVNlcnZlciIsImV4cCI6MTcwMjkxNzQ3NSwiaWF0IjoxNzAyODMxMDc1LCJzdWIiOiIwMDE3NjYuMTg3OTVkNzE4NDU0NGM2ZWJjMWFhZGJhYTc4NWM4OGUuMTAzMiIsImNfaGFzaCI6ImVzd09vWjI5aVBtRG9TeXZXRUdlNVEiLCJlbWFpbCI6ImxkaGRldmVsb3BAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNzAyODMxMDc1LCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.J5oOn5W1NOx599myF3AesuUlBg80mUedGNrUie1Xf6uSIdBNHoXWAylZd3P1eWmnfcGVPYCuvFVeT0ITrSKXLSE6KyAilza9UJOj2aL6EHU4WvDhL1no_HyjZiyXmFwnx8_Mkzc0gCV2k-0q5-COO161WCL690y5eyTYrudIBQO7_iRDRGuuxBaidJ49CTjCbRa68h6zyHvv26I0ziZ6tkim2Y-33vCRGuguh7NklmL4-nyMJlFcwsa4iBeizEUzIPGzYPHnMuMGHdBKWCdCal8hbZNp_kjQV4sCzN9FOQItISrAPigcnM3RjTXGT4u_gscPN0QE2mnFpVLbMhgILQ");

        try {
            LoginDTO loginDTO=loginService.authToken(appleDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void  make_InviteCd() {
        StringBuilder stringBuilder = new StringBuilder();
        Random rd = new Random();
        for(int i=0;i<4;i++){
            stringBuilder.append((char)(rd.nextInt(26)+65));
        }
        for(int i=0;i<4;i++){
            stringBuilder.append(rd.nextInt(10));
        }
        System.out.println("stringBuilder = " + stringBuilder);
    }
}
/*
인코딩 전: {
"kid":"YuyXoY",
"alg":"RS256"
}
인코딩 전: {
"iss":"https://appleid.apple.com",
"aud":"com.dduckdori.SsdamServer",
"exp":1702917475,
"iat":1702831075,
"sub":"001766.18795d7184544c6ebc1aadbaa785c88e.1032",
"c_hash":"eswOoZ29iPmDoSyvWEGe5Q",
"email":"ldhdevelop@gmail.com",
"email_verified":"true",
"auth_time":1702831075,
"nonce_supported":true
}

 */
// 암호화 되어있는 signature 증명 -> 공개키를 통해 signature를 복호화하여 header값과 payload값과 같은지 비교
// 공개키 받아와야 함,