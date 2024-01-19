package com.dduckdori.ssdam_server.Answer;

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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AnswerControllerTest {
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
    @Test
    @DisplayName("해당 질문의 내 답변 조회 실패")
    @WithMockUser
    public void Get_Ssdam_Answer_Fail() throws Exception{
        String id = "1_3_PBAD3758";
        this.mockMvc
                .perform(get("/ssdam/answer/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    @DisplayName("해당 질문의 내 답변 조회, 멤버 아이디 없음")
    @WithMockUser
    public void Get_Ssdam_Answer_No_MemId() throws Exception{
        String id = "1_7_PBAD3758";
        this.mockMvc
                .perform(get("/ssdam/answer/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Transactional
    @DisplayName("답변_저장_실패")
    @WithMockUser
    public void Save_Answer_Fail() throws Exception{
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setCate_id(1);
        answerDTO.setQust_id(2);
        answerDTO.setMem_id(1);
        answerDTO.setInvite_cd("XRHV3887");
        answerDTO.setAns_cn("ㅁㅜㅇㅑㅎㅗ");

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
        answerDTO.setQust_id(4);
        answerDTO.setMem_id(1);
        answerDTO.setInvite_cd("EXRD5370");
        answerDTO.setAns_cn("ㅁㅜㅇㅑㅎㅗ");

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
        answerDTO.setQust_id(7);
        answerDTO.setMem_id(1);
        answerDTO.setInvite_cd("PBAD3758");
        answerDTO.setAns_cn("Test입니다!12");

        this.mockMvc
                .perform(patch("/ssdam/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerDTO))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Transactional
    @DisplayName("답변_수정_실패")
    @WithMockUser
    public void 답변_수정_실패() throws Exception{
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setCate_id(2);
        answerDTO.setQust_id(7);
        answerDTO.setMem_id(3);
        answerDTO.setInvite_cd("PBAD3758");
        answerDTO.setAns_cn("Test입니다!");

        this.mockMvc
                .perform(patch("/ssdam/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerDTO))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    @DisplayName("초대코드별_답변완료_날짜")
    @WithMockUser
    public void 초대코드별_답변완료_날짜() throws Exception{
        String invite_cd = "aaaa1234";
        String year_month = "202401";
        this.mockMvc
                .perform(get("/ssdam/answer/"+invite_cd+"/"+year_month)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}