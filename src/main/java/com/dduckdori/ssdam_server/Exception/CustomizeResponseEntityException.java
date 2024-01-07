package com.dduckdori.ssdam_server.Exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.nio.charset.Charset;
import java.text.ParseException;

@RestController
@ControllerAdvice
public class CustomizeResponseEntityException {
    HttpHeaders httpHeaders = new HttpHeaders();

    @ExceptionHandler(NotFoundUserException.class)
    public final ResponseEntity<Object> CanNotFoundUserException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail",ex.getMessage());
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TryAgainException.class)
    public final ResponseEntity<Object> CanNotSaveAnswerException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail",ex.getMessage());
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<Object> DuplicateAnswerException(){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail","이미 답변을 완료하였습니다.");
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UnAuthroizedAccessException.class ,ParseException.class, JsonProcessingException.class, JOSEException.class, net.minidev.json.parser.ParseException.class})
    public final ResponseEntity<Object> GeneralException(){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail","로그인에 실패하였습니다. 잠시 후 다시 시도해 주세요.");
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
