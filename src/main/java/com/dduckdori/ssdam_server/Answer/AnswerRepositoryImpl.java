package com.dduckdori.ssdam_server.Answer;

import lombok.RequiredArgsConstructor;
import com.dduckdori.ssdam_server.Mapper.AnswerMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

@Repository

public class AnswerRepositoryImpl implements AnswerRepository{

    private AnswerMapper answerMapper;
    public AnswerRepositoryImpl(AnswerMapper answerMapper){
        this.answerMapper = answerMapper;
    }

    @Override
    public int Save_Answer(AnswerDTO answerDTO) {
            return answerMapper.Save_Answer(answerDTO);
        }

        @Override
        public int Save_Answer_Hist(AnswerDTO answerDTO) {
            return answerMapper.Save_Answer_Hist(answerDTO);
        }

        @Override
        public AnswerDTO[] Find_Answer(AnswerDTO answerDTO) throws SQLIntegrityConstraintViolationException {
            return answerMapper.Find_Answer(answerDTO);
        }

        @Override
        public int InviteCd_Ans_yn(String inviteCd) {
            return answerMapper.Find_Non_Ans_Num(inviteCd);
        }

        @Override
        public int Update_Answer(AnswerDTO answerDTO){
        return answerMapper.Update_Answer(answerDTO);
    }

        @Override
        public CompleteDTO complete_answer_YN(AnswerDTO answerDTO) {
            return answerMapper.Complete_Answer_YN(answerDTO);
        }

    @Override
    public CompleteDTO[] Find_Complete_Ans_date(HashMap<String, String> hashMap) {
        return answerMapper.Find_Complete_Ans_date(hashMap);
    }
}
