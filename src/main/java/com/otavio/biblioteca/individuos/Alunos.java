package com.otavio.biblioteca.individuos;

public class Alunos extends Usuario {
    private String curso;
    private String periodo;

    public Alunos(String nome,String senha,String matricula,String curso,String periodo) {
        super(nome,senha,matricula);
        this.curso = curso;
        this.periodo = periodo;
    }


    @Override
    public  double calcularMulta(int dias) {
        return 5 * dias;
    }
}
