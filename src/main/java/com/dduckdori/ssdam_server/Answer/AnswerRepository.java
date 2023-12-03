package com.dduckdori.ssdam_server.Answer;

public interface AnswerRepository
{
    int Save_Answer(AnswerDTO answerDTO);
    int Save_Answer_Hist(AnswerDTO answerDTO);
    AnswerDTO[] Find_Answer(AnswerDTO answerDTO);
    int InviteCd_Ans_yn(String inviteCd);

    int Update_Answer(AnswerDTO answerDTO);
}
