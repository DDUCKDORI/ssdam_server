package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Answer.AnswerList;
import com.dduckdori.ssdam_server.Exception.NotFoundException;
import com.dduckdori.ssdam_server.Response.DateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    @Override
    public QuestionDTO find_question(QuestionDTO questionDTO) {
        QuestionDTO ans_questionDto = questionRepository.find_question(questionDTO);
        if(ans_questionDto == null){
            return null;
        }
        int Ans_Num = questionRepository.find_ans_num(ans_questionDto);
        int Family_Mem_Num = questionRepository.FamilyNum(questionDTO.getInvite_cd());

        if(ans_questionDto.getAns_cn() != null){
            ans_questionDto.setRpy_yn("true");
        }
        else{
            ans_questionDto.setRpy_yn("false");
        }
        ans_questionDto.setNon_ans_num(Family_Mem_Num-Ans_Num);
        return ans_questionDto;
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
            throw new NotFoundException("해당 날짜에 질문이 없어요..");
        }
        dateResponse.setInvite_cd(inviteCd);
        AnswerList[] answerLists = questionRepository.find_answer_date(dateResponse);
        dateResponse.setResult("Success");
        dateResponse.setAns_list(answerLists);
        return dateResponse;
    }

}
