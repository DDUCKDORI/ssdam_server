package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository{
    private QuestionMapper questionMapper;
    public QuestionRepositoryImpl(QuestionMapper questionMapper){
        this.questionMapper=questionMapper;
    }
    @Override
    public QuestionDTO find_question(QuestionDTO questionDTO) {
        return questionMapper.find_question(questionDTO);
    }

    @Override
    public int FamilyNum(String inviteCd) {
        return questionMapper.familyNum(inviteCd);
    }
}
