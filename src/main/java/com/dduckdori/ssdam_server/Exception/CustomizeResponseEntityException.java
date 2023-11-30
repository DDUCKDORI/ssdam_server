package com.dduckdori.ssdam_server.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizeResponseEntityException {
    @ExceptionHandler(NotFoundUserException.class)
    public final ResponseEntity<Object> CanNotFoundUserException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail",ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TryAgainException.class)
    public final ResponseEntity<Object> CanNotSaveAnswerException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail",ex.getMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
