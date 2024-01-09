package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.Question.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    QuestionDTO find_question(QuestionDTO questionDTO);

    int familyNum(String inviteCd);
}
