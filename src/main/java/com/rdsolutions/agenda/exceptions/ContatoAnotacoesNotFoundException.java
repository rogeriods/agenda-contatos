package com.rdsolutions.agenda.exceptions;

public class ContatoAnotacoesNotFoundException extends RuntimeException {
    
    public ContatoAnotacoesNotFoundException(Long id) {
        super("A anotação [ID=" + id + "] não pode ser encontrado");
    }
}
