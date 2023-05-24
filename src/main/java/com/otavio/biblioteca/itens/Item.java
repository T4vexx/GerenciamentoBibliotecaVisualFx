package com.otavio.biblioteca.itens;

import com.otavio.dtos.Emprestavel;

/**
 * Item
 * Classe que representa um item da biblioteca.
 * É uma classe abstrata, fornecendo funcionalidades básicas e comuns a todos os tipos de itens sendo herdadas por eles.
 * Implementa um interface Emprestavel.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public abstract class Item implements Emprestavel {
    private String titulo;
    private String autor;
    private String anoPublicacao;
    private int quantidadeDisponivel;
    private int quantidadeEmprestada;

    /**
     * Construtor dos atributos de um item
     * 
     * @param titulo Titulo de um item.
     * @param autor Titulo de um item.
     * @param anoPublicacao Ano publicacao de um item.
     * @param quantidadeDisponivel Quantidade disponivel de um item.
     */
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

    /**
     * Método implementado da interface Emprestavel.
     * Diminui a quantidade disponivel e aumenta a quantidade emprestados
     */
    public void emprestar() {
        setQuantidadeDisponivel(getQuantidadeDisponivel() - 1);
        setQuantidadeEmprestada(getQuantidadeEmprestada() + 1);
    }

    /**
     * Método implementado da interface Emprestavel.
     * Diminui a quantidade emprestados e aumenta a quantidade disponivel
     */
    public void devolver() {
        setQuantidadeDisponivel(getQuantidadeDisponivel() + 1);
        setQuantidadeEmprestada(getQuantidadeEmprestada() - 1);
    }
}
