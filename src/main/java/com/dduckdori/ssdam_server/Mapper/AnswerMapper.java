package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.Answer.AnswerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerMapper {
    int Save_Answer(AnswerDTO answerDTO);
    int Save_Answer_Hist(AnswerDTO answerDTO);
    AnswerDTO[] Find_Answer(AnswerDTO answerDTO);
    int Find_Non_Ans_Num(String inviteCd);
}
