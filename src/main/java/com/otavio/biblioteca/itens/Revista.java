package com.otavio.biblioteca.itens;

import com.otavio.exceptions.AttempLibraryError;

/**
 * Revista
 * A classe Revista representa um item revista, que herda as propriedades da classe pai Item.
 * Possui informações especializadas sobre o volume e numero de uma revista.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class Revista extends Item {
    private int volume;
    private int numero;

    /**
     * Inicializa os atributos de um Revista.
     * 
     * @param titulo Titulo de um revista String.
     * @param autor Autor de um revista String.
     * @param anoDePublicacao Ano de publicacao de um revista String.
     * @param quantidadeDisponivel Quantidade disponivel de um revista String.
     * @param volume Editora de um revista String.
     * @param numero ISBN de um revista String.
    */
    public Revista(String titulo, String autor, String anoDePublicacao, int quantidadeDisponivel, int volume, int numero) {
        super(titulo,autor,anoDePublicacao,quantidadeDisponivel);
        this.volume = volume;
        this.numero = numero;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
