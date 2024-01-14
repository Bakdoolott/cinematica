package com.mega.cinematica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(RepeatedValueException.class)
    public ResponseEntity<?> repeatedValue(RepeatedValueException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotSavedException.class)
    public ResponseEntity<?> notSavedException(NotSavedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
    @ExceptionHandler(UnexpectedNewException.class)
    public ResponseEntity<?> unexpectedException(UnexpectedNewException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
