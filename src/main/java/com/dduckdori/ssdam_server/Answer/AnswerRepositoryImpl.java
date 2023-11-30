package com.dduckdori.ssdam_server.Answer;

import lombok.RequiredArgsConstructor;
import com.dduckdori.ssdam_server.Mapper.AnswerMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository{
    private final AnswerMapper answerMapper;

    @Override
    public int Save_Answer(AnswerDTO answerDTO) {
        return answerMapper.Save_Answer(answerDTO);
    }

    @Override
    public int Save_Answer_Hist(AnswerDTO answerDTO) {
        return answerMapper.Save_Answer_Hist(answerDTO);
    }

    @Override
    public AnswerDTO[] Find_Answer(AnswerDTO answerDTO) {
        return answerMapper.Find_Answer(answerDTO);
    }
}
