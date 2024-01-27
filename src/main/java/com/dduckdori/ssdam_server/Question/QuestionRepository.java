package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Answer.AnswerList;
import com.dduckdori.ssdam_server.Response.DateResponse;

import java.util.HashMap;

public interface QuestionRepository {
    public QuestionDTO find_question(QuestionDTO questionDTO);

    int FamilyNum(String inviteCd);

    DateResponse find_question_date(HashMap<String, String> param);

    AnswerList[] find_answer_date(DateResponse dateResponse);

    int find_ans_num(QuestionDTO questionDTO);
}
