package com.springboot.demoSpring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // this will handle exception across main project
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) // will run when ever any runtime exception occur
    public ResponseEntity<String> handleRuntimeException(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // client will get readable error message
    }
    // generic exception handle
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> HandleOtherException(Exception e){
        return new ResponseEntity<>("⚠️ Internal Server Error: " +
                e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
