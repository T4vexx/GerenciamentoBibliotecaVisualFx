package com.otavio.exceptions;

/**
 * NotBorrowedItemError
 * Classe que armazena um erro quando um usuario tenta devolver um item nao emprestado
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class NotBorrowedItemError extends RuntimeException {
    public NotBorrowedItemError(String message) {
        super(message);
    }
}
