package com.ada.economizaapi.exceptions;

public class EntidadeJaExisteException extends RuntimeException {

    public EntidadeJaExisteException() {
        super("Entidade já existe.");
    }

    public EntidadeJaExisteException(String message) {
        super(message);
    }
}
