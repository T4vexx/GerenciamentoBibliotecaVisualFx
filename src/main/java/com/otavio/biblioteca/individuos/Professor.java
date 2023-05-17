package com.otavio.biblioteca.individuos;

public class Professor extends Usuario {
    private String departamento;
    private String titulacao;

    public Professor(String nome,String senha,String matricula,String departamento,String titulacao) {
        super(nome,senha,matricula);
        this.departamento = departamento;
        this.titulacao = titulacao;
    }

    @Override
    public double calcularMulta(int dias) {
        return 6.25 * dias;
    }
}
