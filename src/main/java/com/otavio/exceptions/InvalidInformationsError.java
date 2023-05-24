package com.otavio.exceptions;

/**
 * InvalidInformationsError
 * Classe que armazena um erro de uma informação que foi passada invalida
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class InvalidInformationsError extends RuntimeException {
    public InvalidInformationsError(String message) {
        super(message);
    }

}