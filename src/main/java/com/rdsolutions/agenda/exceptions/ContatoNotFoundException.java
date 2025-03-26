package com.rdsolutions.agenda.exceptions;

public class ContatoNotFoundException extends RuntimeException {

    public ContatoNotFoundException(Long id) {
        super("O contato [ID=" + id + "] não pode ser encontrado");
    }
}
