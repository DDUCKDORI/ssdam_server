package com.dduckdori.ssdam_server.Answer;

import com.dduckdori.ssdam_server.Mapper.AnswerMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerRepositoryImplTest {
    private static AnswerMapper answerMapper;
    @Autowired
    public AnswerRepositoryImplTest(AnswerMapper answerMapper){
        this.answerMapper=answerMapper;
    }

    @Test
    @DisplayName("답변_저장_성공")
    @Transactional
    public void Save_Answer_Success(){
        AnswerDTO test_answerDTO = new AnswerDTO();
        test_answerDTO.setInvite_cd("AAAAA00000");
        test_answerDTO.setQust_id(1);
        test_answerDTO.setCate_id(1);
        test_answerDTO.setMem_id(2);
        test_answerDTO.setAns_cn("TESTEST_AAAAA00000");
        test_answerDTO.setFst_inpr("AAAAA00000_2");
        test_answerDTO.setLast_updr("AAAAA00000_2");

        int test_result=answerMapper.Save_Answer(test_answerDTO);
        Assertions.assertThat(test_result).isNotNull();
    }
    @Test
    @DisplayName("나의_답변_조회")
    public void Find_Answer() throws SQLIntegrityConstraintViolationException{
        AnswerDTO test_answerDTO = new AnswerDTO();
        test_answerDTO.setInvite_cd("AAAAA00000");
        test_answerDTO.setQust_id(1);
        test_answerDTO.setCate_id(1);
        test_answerDTO.setMem_id(2);
        AnswerDTO[] test_list = answerMapper.Find_Answer(test_answerDTO);
        System.out.println("test_list = " + test_list);
        System.out.println("test_list.length = " + test_list.length);
    }
    @Test
    @DisplayName("초대코드별_답변_조회")
    public void Find_Answer_Invite_Cd() throws SQLIntegrityConstraintViolationException {
        AnswerDTO test_answerDTO = new AnswerDTO();
        test_answerDTO.setInvite_cd("AAAAA00000");
        test_answerDTO.setQust_id(1);
        test_answerDTO.setCate_id(1);
        AnswerDTO[] test_list = answerMapper.Find_Answer(test_answerDTO);
        System.out.println("test_list = " + test_list);
        System.out.println("test_list.length = " + test_list.length);
    }
    @Test
    @DisplayName("특정_초대코드_미답변_수")
    public void Find_InviteCd_Non_Ans_Num(){
        String inviteCd="BBBBB00000";
        int result = answerMapper.Find_Non_Ans_Num(inviteCd);
        System.out.println("result = " + result);
    }
    @Test
    @DisplayName("답변_완료_여부")
    public void Complete_Answer_YN(){
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setInvite_cd("PBAD3758");
        answerDTO.setCate_id(1);
        answerDTO.setQust_id(7);
        CompleteDTO completeDTO = answerMapper.Complete_Answer_YN(answerDTO);
        System.out.println("completeDTO = " + completeDTO);
    }
    @Test
    public void Complete_answerlist(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Invite_cd","aaaa1234");
        hashMap.put("Year_Month","202401");
        CompleteDTO[] completeDTOS = answerMapper.Find_Complete_Ans_date(hashMap);
        for (CompleteDTO completeDTO : completeDTOS){
            System.out.println("completeDTO = " + completeDTO);
        }
    }
}