package com.otavio.biblioteca.itens;

import com.otavio.exceptions.AttempLibraryError;

;

public class Livro extends Item {
        public String editora;
        public String ISBN;

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
