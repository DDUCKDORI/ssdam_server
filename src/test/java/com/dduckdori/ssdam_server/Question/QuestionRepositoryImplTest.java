package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Mapper.QuestionMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class QuestionRepositoryImplTest {

    private final QuestionMapper questionMapper;

    @Autowired
    QuestionRepositoryImplTest(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Test
    void find_question() {
        QuestionDTO questionDTO=new QuestionDTO("AAAAA00000",1);
        QuestionDTO success_questionDTO=questionMapper.find_question(questionDTO);
        Assertions.assertThat(success_questionDTO).isNotNull();
    }
}