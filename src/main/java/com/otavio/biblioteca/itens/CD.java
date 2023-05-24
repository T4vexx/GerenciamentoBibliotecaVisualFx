package com.otavio.biblioteca.itens;

import com.otavio.exceptions.AttempLibraryError;

/**
 * CD
 * A classe CD representa um item cd, que herda as propriedades da classe pai Item.
 * Possui informações especializadas sobre a gravador e volume de um cd.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class CD extends Item {
    private int volume;
    private String gravadora;

    /**
     * Inicializa os atributos de um cd.
     * 
     * @param titulo Titulo de um cd String.
     * @param autor Autor de um cd String.
     * @param anoDePublicacao Ano de publicação de um cd String.
     * @param quantidadeDisponivel Quantidade disponivel de um cd String.
     * @param volume Volume de um cd String.
     * @param gravadora Gravadora de um cd String.
     */
    public CD(String titulo, String autor, String anoDePublicacao, int quantidadeDisponivel, int volume, String gravadora) {
        super(titulo,autor,anoDePublicacao,quantidadeDisponivel);
        this.volume = volume;
        this.gravadora = gravadora;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }
}
