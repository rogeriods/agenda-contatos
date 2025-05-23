package com.rdsolutions.agenda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContatoNotFoundAdvice {

    @ExceptionHandler(ContatoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String contatoNotFoundHandler(ContatoNotFoundException ex) {
        return ex.getMessage();
    }
}
