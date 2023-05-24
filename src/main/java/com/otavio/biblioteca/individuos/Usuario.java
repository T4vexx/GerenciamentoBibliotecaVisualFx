package com.otavio.biblioteca.individuos;

import com.otavio.biblioteca.itens.Emprestimo;
import java.util.ArrayList;


/**
 * Usuario
 * A classe Usuario representa um usuário da biblioeca.
 * É uma classe abstrata, fornecendo funcionalidades básicas e comuns a todos os tipos de usuários sendo herdadas por eles.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public abstract class Usuario {
    private String nome;
    private String matricula;
    private String senha;
    private ArrayList<Emprestimo> itensEmprestados;

    /**
     * Construtor da classe"Usuario que inicializa as propriedades do usuário.
     *
     * @param nome O nome do usuário String.
     * @param matricula A matrícula do usuário String.
     * @param senha A senha do usuário String.
     */
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

    /**
     * Método que recebe um Emprestimo e adiciona na lista de emprestimos do cliente.
     * 
     * @param item Um item do tipo Emprestimo.
     * @return Retorna um boolean caso o emprestimo der certo.
     */
    public boolean addItemEmprestado(Emprestimo item) {
        boolean flag = false;
        for(Emprestimo i: itensEmprestados) {
            if (i.getItem().getTitulo().equalsIgnoreCase(item.getItem().getTitulo())) {
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

    /**
     * Método que valida a senha para o login.
     * 
     * @param senha senha de um usuario String.
     * @return retorna se a senha esta correta ou nao.
     */
    public boolean validarSenha(String senha) {
        return getSenha().equals(senha);
    }

    /**
     *Método que calcula a multa com base no número de dias de atraso.

     * @param dias O número de dias de atraso int.
     * @return O valor da multa calculado com base nos dias de atraso.
     */
    public abstract double calcularMulta(int dias);

}
