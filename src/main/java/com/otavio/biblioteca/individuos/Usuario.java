package com.otavio.biblioteca.individuos;

import com.otavio.biblioteca.itens.Emprestimo;
import java.util.ArrayList;

public abstract class Usuario {
    private String nome;
    private String matricula;
    private String senha;
    private ArrayList<Emprestimo> itensEmprestados;

    public Usuario(String nome,String matricula,String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        itensEmprestados = new ArrayList<Emprestimo>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Emprestimo> getItensEmprestados() {
        return itensEmprestados;
    }

    public boolean addItemEmprestado(Emprestimo item) {
        boolean flag = false;
        for(Emprestimo i: itensEmprestados) {
            if (i.getItem().getTitulo().equals(item.getItem().getTitulo())) {
                flag = true;
                break;
            }
        }
        if(flag) {
            return false;
        } else {
            this.itensEmprestados.add(item);
            return true;
        }

    }

    public boolean validarSenha(String senha) {
        return getSenha().equals(senha);
    }
    public abstract double calcularMulta(int dias);

}
