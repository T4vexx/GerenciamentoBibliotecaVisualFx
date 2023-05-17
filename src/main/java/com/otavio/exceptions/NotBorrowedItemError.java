package com.otavio.exceptions;

public class NotBorrowedItemError extends RuntimeException {
    public NotBorrowedItemError(String message) {
        super(message);
    }
}
