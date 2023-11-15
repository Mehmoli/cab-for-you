package com.novi.cabforyou.controllers;

import com.novi.cabforyou.exceptions.BadRequestException;
import com.novi.cabforyou.exceptions.FileNotFoundException;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseEntity<Object> exception(FileNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<String> exception(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String> exception(UsernameNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}