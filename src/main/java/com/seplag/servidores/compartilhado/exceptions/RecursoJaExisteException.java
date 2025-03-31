package com.seplag.servidores.compartilhado.exceptions;

public class RecursoJaExisteException extends RuntimeException {

    public RecursoJaExisteException(String message) {
        super(message);
    }
}
