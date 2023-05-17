package com.otavio.biblioteca;

import com.otavio.biblioteca.itens.CD;
import com.otavio.biblioteca.itens.Item;
import com.otavio.biblioteca.itens.Livro;
import com.otavio.biblioteca.itens.Revista;
import com.otavio.exceptions.InvalidInformationsError;

import java.util.ArrayList;

public class Vitrine {
    private ArrayList<Livro> livros = new ArrayList<Livro>();
    private ArrayList<CD> cds = new ArrayList<CD>();
    private ArrayList<Revista> revistas = new ArrayList<Revista>();

    public void setNewLivro(Livro l) throws InvalidInformationsError {
        if(livros.contains(l)) {
            throw new InvalidInformationsError("Informações inválidas | Item já existente");
        }
        livros.add(l);
    }
    public void setNewCd(CD c) throws InvalidInformationsError {
        if(cds.contains(c)) {
            throw new InvalidInformationsError("Informações inválidas | Item já existente");
        }
        cds.add(c);
    }
    public void setNewRevista(Revista r) throws InvalidInformationsError {
        if(revistas.contains(r)) {
            throw new InvalidInformationsError("Informações inválidas | Item já existente");
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
