package com.dduckdori.ssdam_server.Question;

import com.dduckdori.ssdam_server.Exception.NotFoundUserException;
import com.dduckdori.ssdam_server.Response.EtcResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.net.URI;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/ssdam/question/{id}")
    public ResponseEntity<QuestionDTO> find_Question(@PathVariable String id){
        String Invite_cd=id.split("_")[0];
        int Mem_id=Integer.parseInt(id.split("_")[1]);
        QuestionDTO questionDTO=new QuestionDTO(Invite_cd,Mem_id);
        questionDTO=questionService.find_question(questionDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if(questionDTO==null){
            throw new NotFoundUserException("해당 사용자를 찾을 수 없습니다..");
        }
        questionDTO.setResult("Success");

        return new ResponseEntity<>(questionDTO,httpHeaders, HttpStatus.OK);
    }
    @GetMapping("/ssdam/family/{invite_cd}")
    public ResponseEntity<Object> find_FamilyNum(@PathVariable String invite_cd){
        int fam_num = questionService.FamilyNum(invite_cd);
        if(fam_num<0){
            throw new NotFoundUserException("해당하는 초대코드가 없어요..");
        }
        EtcResponse etcResponse = new EtcResponse();
        etcResponse.setResult("success");
        etcResponse.setFamily_num(fam_num);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(etcResponse,httpHeaders,HttpStatus.OK);
    }
}
