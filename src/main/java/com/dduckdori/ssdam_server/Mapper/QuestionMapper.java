package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.Answer.AnswerList;
import com.dduckdori.ssdam_server.Question.QuestionDTO;
import com.dduckdori.ssdam_server.Response.DateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface QuestionMapper {
    QuestionDTO find_question(QuestionDTO questionDTO);

    int familyNum(String inviteCd);

    DateResponse find_question_specific_date(HashMap<String, String> param);

    AnswerList[] find_answer_specific_date(DateResponse dateResponse);

    int find_ans_num(QuestionDTO questionDTO);
}
