package com.otavio.exceptions;

/**
 * AttempLibraryError
 * Classe que armazena um erro em alguma tentativa na biblioteca
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class AttempLibraryError extends RuntimeException{
    public AttempLibraryError(String message) {
        super(message);
    }
}
