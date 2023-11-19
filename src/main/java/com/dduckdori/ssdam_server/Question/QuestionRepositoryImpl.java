package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository{
    private final QuestionMapper questionMapper;
    @Override
    public QuestionDTO find_question(QuestionDTO questionDTO) {

        return questionMapper.find_qeustion(questionDTO);
    }
}
