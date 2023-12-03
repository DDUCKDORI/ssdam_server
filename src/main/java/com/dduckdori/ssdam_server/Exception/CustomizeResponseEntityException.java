package com.dduckdori.ssdam_server.Exception;

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
}
