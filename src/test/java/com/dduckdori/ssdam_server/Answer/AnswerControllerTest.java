package com.dduckdori.ssdam_server.Answer;

import com.dduckdori.ssdam_server.Exception.UnAuthroizedAccessException;
import com.dduckdori.ssdam_server.Login.AppleDTO;
import com.dduckdori.ssdam_server.Login.LoginController;
import com.dduckdori.ssdam_server.Login.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AnswerControllerTest {
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private LoginController loginController;
    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
    @Test
    @WithMockUser
    public void appleCallBack() throws Exception, UnAuthroizedAccessException {

        AppleDTO appleDTO = new AppleDTO();
        appleDTO.setCode("c1242acd89dd74547a46ed56a17ceb7f4.0.ryzw.KUaZWBEDUPN96yKoiY5qkQ");
        appleDTO.setId_token("eyJraWQiOiJmaDZCczhDIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRkdWNrZG9yaS5Tc2RhbSIsImV4cCI6MTcwNDQ2NTk0MCwiaWF0IjoxNzA0Mzc5NTQwLCJzdWIiOiIwMDA4OTYuODRmYWIwMjk0ZDY5NDI5ZWFjN2YwZTFlMjNlNTMyOGYuMDkxNiIsImNfaGFzaCI6IjNXd3RweloyT3h6TFUyT3lQVVFwWVEiLCJhdXRoX3RpbWUiOjE3MDQzNzk1NDAsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.NZXEzSI9iKd89g-ZtKTpjQpyU8oNANASHHAy_8Y0w3oAfy8n1pbTXegKOw0bsad6CoSwsM94VS7lZBR20vcaDoiusTxVl0jiX8USpC1g-hKtK5EY73hWPqTyadIAI7K_0g91Dy2upL-Q1MwDrnU9NxMWXKyV3d2MGmRXQ3pG72Z0k-4VVQTPHHxd_ai6YIyt_-YWN0XEtCp8Cc9k55PZZ_G12hl5YkH2zByOVuq9U_eHKKLwtgv5scPeBw8KuZq7v-KlMIFwYE71eevdUK_3ZWb4S1F6ik0QUyvNDrjKsEbeVR-yMRJagAIKzHplYWlXyCQNWkWF7aWQH_PRbdIF7g");
        loginController.appleCallBack(appleDTO);
        this.mockMvc
                .perform(post("/ssdam/apple/login/callback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appleDTO))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    @DisplayName("답변_저장_실패")
    @WithMockUser
    public void Save_Answer_Fail() throws Exception{
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setCate_id(1);
        answerDTO.setQust_id(1);
        answerDTO.setMem_id(2);
        answerDTO.setInvite_cd("AAAAA00000");

        this.mockMvc
                .perform(post("/ssdam/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerDTO))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    @Transactional
    @DisplayName("답변_저장_성공")
    @WithMockUser
    public void Save_Answer_Success() throws Exception{
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setCate_id(1);
        answerDTO.setQust_id(2);
        answerDTO.setMem_id(1);
        answerDTO.setInvite_cd("BBBBB00000");
        answerDTO.setAns_cn("Test입니다!");

        this.mockMvc
                .perform(post("/ssdam/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerDTO))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Transactional
    @DisplayName("답변_수정_성공")
    @WithMockUser
    public void 답변_수정_성공() throws Exception{
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setCate_id(1);
        answerDTO.setQust_id(2);
        answerDTO.setMem_id(2);
        answerDTO.setInvite_cd("BBBBB00000");
        answerDTO.setAns_cn("Test입니다!");

        this.mockMvc
                .perform(patch("/ssdam/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerDTO))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}