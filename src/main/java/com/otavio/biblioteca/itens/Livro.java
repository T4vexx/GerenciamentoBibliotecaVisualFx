package com.otavio.biblioteca.itens;

import com.otavio.exceptions.AttempLibraryError;


/**
 * Livro
 * A classe Livro representa um item livro, que herda as propriedades da classe pai Item.
 * Possui informações especializadas sobre a editora e ISBN de um livro.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class Livro extends Item {
        public String editora;
        public String ISBN;

        /**
         * Inicializa os atributos de um livro.
         * 
         * @param titulo Titulo de um livro String.
         * @param autor Autor de um livro String.
         * @param anoDePublicacao Ano de publicacao de um livro String.
         * @param quantidadeDisponivel Quantidade disponivel de um livro String.
         * @param editora Editora de um livro String.
         * @param ISBN ISBN de um livro String.
         */
        public Livro(String titulo, String autor, String anoDePublicacao, int quantidadeDisponivel, String editora, String ISBN) {
                super(titulo,autor,anoDePublicacao,quantidadeDisponivel);
                this.editora = editora;
                this.ISBN = ISBN;
        }

        public String getEditora() {
                return editora;
        }

        public void setEditora(String editora) {
                this.editora = editora;
        }

        public String getISBN() {
                return ISBN;
        }

        public void setISBN(String ISBN) {
                this.ISBN = ISBN;
        }
}
