package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Response.DateResponse;

import java.util.HashMap;

public interface QuestionService {
    public QuestionDTO find_question(QuestionDTO questionDTO);
    int FamilyNum(String inviteCd);

    DateResponse find_question_date(String date, String inviteCd);
}
