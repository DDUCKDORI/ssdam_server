package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.Answer.AnswerDTO;
import com.dduckdori.ssdam_server.Answer.CompleteDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.sql.SQLIntegrityConstraintViolationException;


@Mapper
public interface AnswerMapper {
    int Save_Answer(AnswerDTO answerDTO);
    int Save_Answer_Hist(AnswerDTO answerDTO);
    AnswerDTO[] Find_Answer(AnswerDTO answerDTO) throws SQLIntegrityConstraintViolationException;
    int Find_Non_Ans_Num(String inviteCd);
    int Update_Answer(AnswerDTO answerDTO);

    CompleteDTO Complete_Answer_YN(AnswerDTO answerDTO);
}
