package com.dduckdori.ssdam_server.Answer;

public interface AnswerRepository
{
    public int Save_Answer(AnswerDTO answerDTO);
    public int Save_Answer_Hist(AnswerDTO answerDTO);
    public AnswerDTO[] Find_Answer(AnswerDTO answerDTO);
    int InviteCd_Ans_yn(String inviteCd);
}
