package com.dduckdori.ssdam_server.Question;

public interface QuestionService {
    public QuestionDTO find_question(QuestionDTO questionDTO);
    int FamilyNum(String inviteCd);
}
