package com.otavio.biblioteca.itens;

import com.otavio.exceptions.AttempLibraryError;

public class CD extends Item {
    private int volume;
    private String gravadora;

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
