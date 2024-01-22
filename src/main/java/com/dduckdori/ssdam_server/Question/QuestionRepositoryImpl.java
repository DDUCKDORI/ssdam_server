package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Answer.AnswerList;
import com.dduckdori.ssdam_server.Mapper.QuestionMapper;
import com.dduckdori.ssdam_server.Response.DateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

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

    @Override
    public DateResponse find_question_date(HashMap<String, String> param) {
        return questionMapper.find_question_specific_date(param);
    }

    @Override
    public AnswerList[] find_answer_date(DateResponse dateResponse) {
        return questionMapper.find_answer_specific_date(dateResponse);
    }

    @Override
    public int find_ans_num(QuestionDTO questionDTO) {
        return questionMapper.find_ans_num(questionDTO);
    }
}
