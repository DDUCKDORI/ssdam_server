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

import java.nio.charset.Charset;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

@RestController
@ControllerAdvice
public class CustomizeResponseEntityException {
    HttpHeaders httpHeaders = new HttpHeaders();

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Object> CanNotFoundAnswerException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail","답변이 등록되지 않았어요..");
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<Object> DuplicateAnswerException(){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail","이미 답변을 완료하였습니다.");
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class, TryAgainException.class,UnAuthroizedAccessException.class ,ParseException.class, JsonProcessingException.class, JOSEException.class, net.minidev.json.parser.ParseException.class})
    public final ResponseEntity<Object> GeneralException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail", ex.getMessage());
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
