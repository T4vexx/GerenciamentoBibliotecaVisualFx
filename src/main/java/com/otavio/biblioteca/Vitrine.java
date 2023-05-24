package com.otavio.biblioteca;

import com.otavio.biblioteca.itens.CD;
import com.otavio.biblioteca.itens.Item;
import com.otavio.biblioteca.itens.Livro;
import com.otavio.biblioteca.itens.Revista;
import com.otavio.exceptions.InvalidInformationsError;

import java.util.ArrayList;

/**
 * Vitrine
 * Assim como uma "vitrine" essa classe armazena os livros, cds e revistas da biblioteca.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class Vitrine {
    private ArrayList<Livro> livros = new ArrayList<Livro>();
    private ArrayList<CD> cds = new ArrayList<CD>();
    private ArrayList<Revista> revistas = new ArrayList<Revista>();

    /**
     * Método que adiciona um novo livro no array.
     * 
     * @param l Um novo livro para adicionar no array Livro.
     * @throws InvalidInformationsError Erro caso ja exista esse livro.
     */
    public void setNewLivro(Livro l) throws InvalidInformationsError {
        for(Livro liv: livros) {
            if(liv.getTitulo().equalsIgnoreCase(l.getTitulo())) {
                throw new InvalidInformationsError("Informações inválidas | Item já existente");
            }
        }
        livros.add(l);
    }

    /**
     * Método que adiciona um novo cd no array.
     * 
     * @param c Um novo cd para adicionar no array CD.
     * @throws InvalidInformationsError Erro caso ja exista esse cd.
     */
    public void setNewCd(CD c) throws InvalidInformationsError {
        for(CD cd: cds) {
            if(cd.getTitulo().equalsIgnoreCase(c.getTitulo())) {
                throw new InvalidInformationsError("Informações inválidas | Item já existente");
            }
        }
        cds.add(c);
    }

    /**
     * Método que adiciona uma nova revista no array.
     * 
     * @param r Uma nova revista para adicionar no array Revista.
     * @throws InvalidInformationsError Erro caso ja exista essa revista.
     */
    public void setNewRevista(Revista r) throws InvalidInformationsError {
        for(Revista rev: revistas) {
            if(rev.getTitulo().equalsIgnoreCase(r.getTitulo())) {
                throw new InvalidInformationsError("Informações inválidas | Item já existente");
            }
        }
        revistas.add(r);
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<CD> getCDs() {
        return cds;
    }

    public ArrayList<Revista> getRevistas() {
        return revistas;
    }

    /**
     * Método que buscar em todos os item um titulo e retorna caso exista.
     * 
     * @param nomeDoItem Um nome de um item.
     * @return Retorna um item do tipo Item.
     */
    public Item buscarItemPeloNome(String nomeDoItem) {
        for(Livro l: livros) {
            if(l.getTitulo().equalsIgnoreCase(nomeDoItem)) {
                return l;
            }
        }
        for(Revista r: revistas) {
            if(r.getTitulo().equalsIgnoreCase(nomeDoItem)) {
                return r;
            }
        }
        for(CD cd: cds) {
            if(cd.getTitulo().equalsIgnoreCase(nomeDoItem)) {
                return cd;
            }
        }
        return null;
    }
}
