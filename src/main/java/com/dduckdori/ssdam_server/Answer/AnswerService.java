package com.dduckdori.ssdam_server.Answer;

public interface AnswerService {
    public int Save_Answer(AnswerDTO answerDTO);
    public int Save_Answer_Hist(AnswerDTO answerDTO);
    public AnswerDTO[] Find_Answer(String id);
}
