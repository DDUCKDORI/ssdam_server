package com.dduckdori.ssdam_server.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController("/ssdam")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/question")
    public QuestionDTO find_Question(@PathVariable String id){
        String Invite_cd=id.split("_")[0];
        int Mem_id=Integer.parseInt(id.split("_")[1]);
        QuestionDTO questionDTO=new QuestionDTO(Invite_cd,Mem_id);
        System.out.println("questionDTO = " + questionDTO);
        return questionService.find_question(questionDTO);
    }
}
