package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Answer.AnswerList;
import com.dduckdori.ssdam_server.Exception.NotFoundUserException;
import com.dduckdori.ssdam_server.Response.DateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    @Override
    public QuestionDTO find_question(QuestionDTO questionDTO) {
        return questionRepository.find_question(questionDTO);
    }

    @Override
    public int FamilyNum(String inviteCd) {
        return questionRepository.FamilyNum(inviteCd);
    }

    @Override
    public DateResponse find_question_date(String date, String inviteCd) {
        HashMap<String,String> param = new HashMap<>();
        param.put("date",date);
        param.put("invite_cd",inviteCd);
        DateResponse dateResponse = questionRepository.find_question_date(param);
        if(dateResponse == null){
            throw new NotFoundUserException("해당 날짜에 질문이 없어요..");
        }
        dateResponse.setInvite_cd(inviteCd);
        AnswerList[] answerLists = questionRepository.find_answer_date(dateResponse);
        dateResponse.setAns_list(answerLists);
        return dateResponse;
    }

}
