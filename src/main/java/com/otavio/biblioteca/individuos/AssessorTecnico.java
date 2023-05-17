package com.otavio.biblioteca.individuos;

public class AssessorTecnico extends Usuario {
    private String secao;

    public AssessorTecnico(String nome,String senha,String matricula,String secao) {
        super(nome,senha,matricula);
        this.secao = secao;
    }
    @Override
    public double calcularMulta(int dias) {
        return 5.75 * dias;
    }
}
