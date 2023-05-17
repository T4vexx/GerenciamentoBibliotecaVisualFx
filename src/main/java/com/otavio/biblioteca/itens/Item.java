package com.otavio.biblioteca.itens;

import com.otavio.dtos.Emprestavel;

public abstract class Item implements Emprestavel {
    private String titulo;
    private String autor;
    private String anoPublicacao;
    private int quantidadeDisponivel;
    private int quantidadeEmprestada;

    public Item(String titulo, String autor, String anoPublicacao, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        quantidadeEmprestada = 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public void setQuantidadeEmprestada(int quantidadeEmprestada) {
        this.quantidadeEmprestada = quantidadeEmprestada;
    }

    public void emprestar() {
        setQuantidadeDisponivel(getQuantidadeDisponivel() - 1);
        setQuantidadeEmprestada(getQuantidadeEmprestada() + 1);
    }
    public void devolver() {
        setQuantidadeDisponivel(getQuantidadeDisponivel() + 1);
        setQuantidadeEmprestada(getQuantidadeEmprestada() - 1);
    }
}
