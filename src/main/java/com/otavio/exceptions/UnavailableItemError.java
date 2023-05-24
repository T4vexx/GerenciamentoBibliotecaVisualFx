package com.otavio.exceptions;

/**
 * UnavailableItemError
 * Classe que armazena um erro de item indisponível
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class UnavailableItemError extends RuntimeException {
    public UnavailableItemError(String message) {
        super(message);
    }
}
