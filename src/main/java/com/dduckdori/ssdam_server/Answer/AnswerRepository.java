package com.dduckdori.ssdam_server.Answer;

import java.sql.SQLIntegrityConstraintViolationException;

public interface AnswerRepository
{
    int Save_Answer(AnswerDTO answerDTO);
    int Save_Answer_Hist(AnswerDTO answerDTO);
    AnswerDTO[] Find_Answer(AnswerDTO answerDTO) throws SQLIntegrityConstraintViolationException;
    int InviteCd_Ans_yn(String inviteCd);

    int Update_Answer(AnswerDTO answerDTO);

    CompleteDTO complete_answer_YN(AnswerDTO answerDTO);
}
