package com.dduckdori.ssdam_server.Answer;

import java.sql.SQLIntegrityConstraintViolationException;

public interface AnswerService {
    int Save_Answer(AnswerDTO answerDTO);
    int Save_Answer_Hist(AnswerDTO answerDTO);
    AnswerDTO[] Find_Answer(String id) throws SQLIntegrityConstraintViolationException;
    int InviteCd_Ans_YN(String inviteCd);
    int Update_Answer(AnswerDTO answerDTO);
}
