package com.dduckdori.ssdam_server.Answer;

import com.dduckdori.ssdam_server.Mapper.AnswerMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    public void Find_Answer(){
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
    public void Find_Answer_Invite_Cd(){
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
}