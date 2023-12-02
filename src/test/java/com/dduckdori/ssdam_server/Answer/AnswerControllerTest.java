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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}