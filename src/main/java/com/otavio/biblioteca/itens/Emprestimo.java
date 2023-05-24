package com.otavio.biblioteca.itens;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Emprestimo
 * Classe que cria um empretimo de um item da biblioteca.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class Emprestimo {
    private Item item;
    private LocalDateTime dataDeEmprestimo;
    private LocalDateTime dataDeDevolucaoPrevista;
    private LocalDateTime dataDeDevolucaoReal = null;

    /**
     * Contrutor que inicia os atributos de um emprestimo.
     * 
     * @param item Item de um emprestimo Item.
     * @param dataDeEmprestimo Data de emprestimo LocalDateTime.
     * @param dataDeDevolucaoPrevista Data de devolução prevista LocalDateTime.
     */
    public Emprestimo(Item item, LocalDateTime dataDeEmprestimo, LocalDateTime dataDeDevolucaoPrevista) {
        this.item = item;
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.dataDeDevolucaoPrevista = dataDeDevolucaoPrevista;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDateTime getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public void setDataDeEmprestimo(LocalDateTime dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
    }

    public LocalDateTime getDataDeDevolucaoPrevista() {
        return dataDeDevolucaoPrevista;
    }

    public void setDataDeDevolucaoPrevista(LocalDateTime dataDeDevolucaoPrevista) {
        this.dataDeDevolucaoPrevista = dataDeDevolucaoPrevista;
    }

    public LocalDateTime getDataDeDevolucaoReal() {
        return dataDeDevolucaoReal;
    }

    public void setDataDeDevolucaoReal(LocalDateTime dataDeDevolucaoReal) {
        this.dataDeDevolucaoReal = dataDeDevolucaoReal;
    }

    /**
     * Método que calcula o tanto de dias entre a data de devolução prevista e a data de devolução real.
     * 
     * @return Diferença de dias long.
     */
    public long calculaMulta() {
        return ChronoUnit.DAYS.between(dataDeDevolucaoPrevista,dataDeDevolucaoReal);
    }
}
