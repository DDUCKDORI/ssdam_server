package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Answer.AnswerList;
import com.dduckdori.ssdam_server.Response.DateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionServiceImplTest {

    private static QuestionService questionService;
    @Autowired
    public QuestionServiceImplTest(QuestionService questionService){
        this.questionService=questionService;
    }
    @Test
    void 특정날짜_초대코드_해당질문_가져오기(){
        DateResponse dateResponse = questionService.find_question_date("20240109","aaaa1234");

        System.out.println("dateResponse = " + dateResponse);
    }

}