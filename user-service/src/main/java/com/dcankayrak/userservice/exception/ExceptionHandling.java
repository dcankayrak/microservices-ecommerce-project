package com.dcankayrak.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> userException(UserException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
