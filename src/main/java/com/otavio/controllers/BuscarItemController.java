package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import com.otavio.biblioteca.itens.CD;
import com.otavio.biblioteca.itens.Item;
import com.otavio.biblioteca.itens.Livro;
import com.otavio.biblioteca.itens.Revista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BuscarItemController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label autor;
    @FXML
    private Label anoPublicacao;
    @FXML
    private Label quantDisp;
    @FXML
    private Label titleOpc1;
    @FXML
    private Label titleOpc2;
    @FXML
    private Label opc1;
    @FXML
    private Label opc2;

    @FXML
    private TextField titulo;
    @FXML
    private Button buscar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
        buscar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Item meuItem;
                meuItem = displayBiblioteca.buscarItem(actionEvent,titulo.getText());
                if(meuItem instanceof Revista) {
                    setLabels(meuItem.getTitulo(),meuItem.getAutor(),meuItem.getAnoPublicacao(),String.valueOf(meuItem.getQuantidadeDisponivel()),"Volume",String.valueOf(((Revista) meuItem).getVolume()),"Numero",String.valueOf(((Revista) meuItem).getNumero()));
                } else if(meuItem instanceof Livro) {
                    setLabels(meuItem.getTitulo(),meuItem.getAutor(),meuItem.getAnoPublicacao(),String.valueOf(meuItem.getQuantidadeDisponivel()),"Editora",String.valueOf(((Livro) meuItem).getEditora()),"ISBN",String.valueOf(((Livro) meuItem).getISBN()));
                } else if(meuItem instanceof CD) {
                    setLabels(meuItem.getTitulo(),meuItem.getAutor(),meuItem.getAnoPublicacao(),String.valueOf(meuItem.getQuantidadeDisponivel()),"Volume",String.valueOf(((CD) meuItem).getVolume()),"Gravadora",String.valueOf(((CD) meuItem).getGravadora()));
                }
            }
        });
    }

    public void setLabels(String titulo, String autor, String anoPublicacao, String quantDisp, String titleOpc1, String opc1, String titleOpc2, String opc2) {
        this.title.setText(titulo);
        this.autor.setText(autor);
        this.anoPublicacao.setText(anoPublicacao);
        this.quantDisp.setText(quantDisp);
        this.opc1.setText(opc1);
        this.opc2.setText(opc2);
        this.titleOpc1.setText(titleOpc1);
        this.titleOpc2.setText(titleOpc2);
        this.title.setOpacity(1);
        this.autor.setOpacity(1);
        this.anoPublicacao.setOpacity(1);
        this.quantDisp.setOpacity(1);
    }
}
