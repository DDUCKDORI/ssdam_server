package com.dduckdori.ssdam_server.Question;

public interface QuestionRepository {
    public QuestionDTO find_question(QuestionDTO questionDTO);

    int FamilyNum(String inviteCd);
}
