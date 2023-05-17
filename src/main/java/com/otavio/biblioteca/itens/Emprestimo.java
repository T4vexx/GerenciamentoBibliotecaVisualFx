package com.otavio.biblioteca.itens;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Item item;
    private LocalDateTime dataDeEmprestimo;
    private LocalDateTime dataDeDevolucaoPrevista;
    private LocalDateTime dataDeDevolucaoReal = null;

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

    public long calculaMulta() {
        return ChronoUnit.DAYS.between(dataDeDevolucaoPrevista,dataDeDevolucaoReal);
    }
}
