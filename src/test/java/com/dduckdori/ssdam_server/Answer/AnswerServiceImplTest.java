package com.dduckdori.ssdam_server.Answer;

import com.dduckdori.ssdam_server.Mapper.AnswerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerServiceImplTest {

    private static AnswerService answerService;
    @Autowired
    public AnswerServiceImplTest(AnswerService answerService){
        this.answerService=answerService;
    }
    @Test
    @DisplayName("답변_수정_성공")
    //@Transactional
    void 답변_수정_성공() {
        AnswerDTO test_answerDTO = new AnswerDTO();
        test_answerDTO.setInvite_cd("AAAAA00000");
        test_answerDTO.setQust_id(1);
        test_answerDTO.setCate_id(1);
        test_answerDTO.setMem_id(2);
        test_answerDTO.setAns_cn("TESTEST_AAAAA00000");
        answerService.Update_Answer(test_answerDTO);
    }
}