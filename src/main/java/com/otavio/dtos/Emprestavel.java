package com.otavio.dtos;

/**
 * Emprestavel
 * Interface que define o contrato para objetos emprestáveis.
 * Os objetos emprestáveis devem ser emprestados e devolvidos.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public interface Emprestavel {

    /**
     * Método que realiza o empréstimo do objeto.
     */
    public void emprestar();

    /**
     * Método que realiza a devolução do objeto.
     */
    public void devolver();
}
