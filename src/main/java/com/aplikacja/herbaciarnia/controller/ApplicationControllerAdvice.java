package com.aplikacja.herbaciarnia.controller;

import com.aplikacja.herbaciarnia.exception.BindingResultSupport;
import com.aplikacja.herbaciarnia.exception.Error;
import com.aplikacja.herbaciarnia.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handle (Exception ex) {
        LOGGER.warn("Something went wrong: {}", ex.getMessage(), ex);
        return Error.builder().message("Something went wrong").build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handle(ResourceNotFoundException ex){
        return Error.builder().message(ex.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(MethodArgumentNotValidException ex){
        return Error.builder().message(BindingResultSupport.asMessage(ex.getBindingResult())).build();
    }
}
