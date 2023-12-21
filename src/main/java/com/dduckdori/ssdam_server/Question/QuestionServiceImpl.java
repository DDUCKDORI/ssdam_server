package com.dduckdori.ssdam_server.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    @Override
    public QuestionDTO find_question(QuestionDTO questionDTO) {
        return questionRepository.find_question(questionDTO);
    }
}
