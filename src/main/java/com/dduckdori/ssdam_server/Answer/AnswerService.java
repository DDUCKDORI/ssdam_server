package com.dduckdori.ssdam_server.Answer;

public interface AnswerService {
    int Save_Answer(AnswerDTO answerDTO);
    int Save_Answer_Hist(AnswerDTO answerDTO);
    AnswerDTO[] Find_Answer(String id);
    int InviteCd_Ans_YN(String inviteCd);
}
