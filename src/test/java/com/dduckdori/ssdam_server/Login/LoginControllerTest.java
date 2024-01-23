package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Answer.AnswerDTO;
import com.dduckdori.ssdam_server.Exception.UnAuthroizedAccessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LoginControllerTest {
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    private LoginService loginService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
    @Autowired
    public LoginControllerTest(LoginService loginService){
        this.loginService=loginService;
    }

    @Test
    void getToken() throws Exception, UnAuthroizedAccessException {
        AppleDTO appleDTO = new AppleDTO("c525dcf65587b40a58dfcd4776859d08b.0.ryzw.GzGSF-pEUnbs8rmF0TOLsA",
                "eyJraWQiOiJXNldjT0tCIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRkdWNrZG9yaS5Tc2RhbSIsImV4cCI6MTcwNTE0NTA0NCwiaWF0IjoxNzA1MDU4NjQ0LCJzdWIiOiIwMDA4OTYuODRmYWIwMjk0ZDY5NDI5ZWFjN2YwZTFlMjNlNTMyOGYuMDkxNiIsImNfaGFzaCI6Im9HQ3o3T0RfNGpoY3BZUmQ5Ry1DUnciLCJhdXRoX3RpbWUiOjE3MDUwNTg2NDQsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.laK5OshGat0u_IrEla2HgzOQimMCS847Lwehq18PXocACG7gVVTBbH_6Vf-eV62AbyRnj_Ln6OoNxRpc89_UdcX8zY-U8RehTKbHzmw_ff11O9RaGszaLU3xt_Ckdfd5MCESOGKOVdgLYSPheLhwgWZB4UBfnfqMk2xi93AhLK2I-L6Wym-dv9Gl19NEAzp4WzDBMH0DkCLN0u_F4EHVsPJ6NIztZj5JrwM8pRuOp6cLBvdbIUV6jDkmyo648iF3Xm5OiXqP46dVq6MjgNPw-M8XjBEXes89O7xeZYbvBT8PSb_WsrV0LKNaJ3bbODyHdKPnRSRj9X16A2IWQGLhhg");
        loginService.getToken(appleDTO);
    }
    @Test
    void authToken() throws Exception{
        AppleDTO appleDTO = new AppleDTO("c04132f144631446d869a00f26f05e8ab.0.ryzw.QUa1vWrzndEO3cwIqsjTOw",
                "eyJraWQiOiJsVkhkT3g4bHRSIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRkdWNrZG9yaS5Tc2RhbSIsImV4cCI6MTcwNTE0ODM3MCwiaWF0IjoxNzA1MDYxOTcwLCJzdWIiOiIwMDA4OTYuODRmYWIwMjk0ZDY5NDI5ZWFjN2YwZTFlMjNlNTMyOGYuMDkxNiIsImNfaGFzaCI6ImtpdnpsZTdoa3hZTktacXcxVUIyT2ciLCJhdXRoX3RpbWUiOjE3MDUwNjE5NzAsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.jW_UUyWeX3d3FhRL_dsmGsZY132yF5wQr2zxuNqSErnKi3oiD4nfvf-r1m3RY0qHzuGOSoIjRTUF669ziwWXV4tM-YdzwokjxVH1JgQm2X9fhEY2Af6vf15mGyFxwWjInl1dv4O-8SKvI_kDAqlkuFwKfUYNY5V5rS2-kWvsArdIAP6fBdtN9wXZpEOTMhntYekAYDLESQnbUBBkleLtMEmD1ppYfLlHqy-Zb9ERgBc62nZ_3O1bb6Kb0iSe29Nka-18dv4Kun1jia6dZG66EkmZpzfqjzKBIya5PUMt6GKNm0n6Le9rVKeHHf_bYNCbG3PHKUSGLjG3f_stQXqGeg");

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

    @Test
    @Transactional
    @DisplayName("회원탈퇴_성공")
    @WithMockUser
    public void logoutMember() throws Exception{
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setInvite_cd("WZFP6785");
        loginDTO.setMem_id(1);

        this.mockMvc
                .perform(post("/ssdam/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Transactional
    @DisplayName("회원탈퇴_실패")
    @WithMockUser
    public void logoutMember_Fail() throws Exception{
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setInvite_cd("WZFP6785");
        loginDTO.setMem_id(100);

        this.mockMvc
                .perform(post("/ssdam/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}
