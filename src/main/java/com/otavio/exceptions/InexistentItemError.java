package com.otavio.exceptions;

public class InexistentItemError extends RuntimeException {
    public InexistentItemError(String message) {
        super(message);
    }
}
