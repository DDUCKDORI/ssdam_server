package com.dduckdori.ssdam_server.Answer;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

public interface AnswerService {
    int Save_Answer(AnswerDTO answerDTO);
    int Save_Answer_Hist(AnswerDTO answerDTO);
    AnswerDTO[] Find_Answer(String id) throws SQLIntegrityConstraintViolationException;
    int InviteCd_Ans_YN(String inviteCd);
    int Update_Answer(AnswerDTO answerDTO);

    int complete_answer_YN(AnswerDTO answerDTO);

    ArrayList<String> Find_Complete_Ans_Date(HashMap<String, String> hashMap);

    int Find_Question(AnswerDTO answerDTO);
}
