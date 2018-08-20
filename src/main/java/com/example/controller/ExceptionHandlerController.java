package com.example.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Server error")
    public void internalServerError(RuntimeException e) {
        e.printStackTrace();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Login already exist")
    public void badRequest() {
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No client/user with current id")
    public void noClient() {
    }

    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No user with current id")
    public void noUser() {
    }

}
