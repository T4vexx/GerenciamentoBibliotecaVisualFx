package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import com.otavio.biblioteca.itens.CD;
import com.otavio.biblioteca.itens.Item;
import com.otavio.biblioteca.itens.Livro;
import com.otavio.biblioteca.itens.Revista;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmprestadoController implements Initializable {

    private int estado = 0;
    @FXML
    private Label messageLabel;
    @FXML
    private TextField index;
    @FXML
    private Button emprestar;
    @FXML
    private Button listarLivro;
    @FXML
    private Button listarRevista;
    @FXML
    private Button ListarCDs;
    @FXML
    private BorderPane bp;
    private ListView<String> list;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
        listarLivro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Livro> livros;
                int count = 0;
                livros = displayBiblioteca.getVitrine().getLivros();
                list = new ListView<String>();
                ObservableList<String> items = FXCollections.observableArrayList();
                for(Livro l: livros) {
                    items.add(count+" - "+l.getTitulo());
                    count++;
                }
                list.setItems(items);
                list.setPrefWidth(226);
                list.setPrefHeight(237);
                bp.setCenter(list);
                estado = 1;
            }
        });
        listarRevista.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Revista> revistas;
                int count = 0;
                revistas = displayBiblioteca.getVitrine().getRevistas();
                list = new ListView<String>();
                ObservableList<String> items = FXCollections.observableArrayList();
                for(Revista r: revistas) {
                    items.add(count+" - "+r.getTitulo());
                    count++;
                }
                list.setItems(items);
                list.setPrefWidth(226);
                list.setPrefHeight(237);
                bp.setCenter(list);
                estado = 2;
            }
        });
        ListarCDs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<CD> cds;
                int count = 0;
                cds = displayBiblioteca.getVitrine().getCDs();
                list = new ListView<String>();
                ObservableList<String> items = FXCollections.observableArrayList();
                for(CD cd: cds) {
                    items.add(count+" - "+cd.getTitulo());
                    count++;
                }
                list.setItems(items);
                list.setPrefWidth(226);
                list.setPrefHeight(237);
                bp.setCenter(list);
                estado = 3;
            }
        });

        emprestar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(estado == 0) {
                    messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                    messageLabel.setText("Você deve escolher uma opção abaixo");
                } else {
                    switch (estado) {
                        case 1 -> {
                            Livro livro = displayBiblioteca.getVitrine().getLivros().get(Integer.parseInt(index.getText()));
                            if (livro != null) {
                                if (displayBiblioteca.fazerEmprestimo(livro)) {
                                    messageLabel.setTextFill(Paint.valueOf("#38A169"));
                                    messageLabel.setText("Item emprestado com sucesso");
                                } else {
                                    messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                                    messageLabel.setText("Voce ja possui esse titulo / quantidade indisponivel");
                                }
                            } else {
                                messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                                messageLabel.setText("Digite um número da lista");
                            }
                        }
                        case 2 -> {
                            Revista revista = displayBiblioteca.getVitrine().getRevistas().get(Integer.parseInt(index.getText()));
                            if (revista != null) {
                                if (displayBiblioteca.fazerEmprestimo(revista)) {
                                    messageLabel.setTextFill(Paint.valueOf("#38A169"));
                                    messageLabel.setText("Item emprestado com sucesso");
                                } else {
                                    messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                                    messageLabel.setText("Voce ja possui esse titulo / quantidade indisponivel");
                                }
                            } else {
                                messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                                messageLabel.setText("Digite um número da lista");
                            }
                        }
                        case 3 -> {
                            CD cds = displayBiblioteca.getVitrine().getCDs().get(Integer.parseInt(index.getText()));
                            if (cds != null) {
                                if (displayBiblioteca.fazerEmprestimo(cds)) {
                                    messageLabel.setTextFill(Paint.valueOf("#38A169"));
                                    messageLabel.setText("Item emprestado com sucesso");
                                } else {
                                    messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                                    messageLabel.setText("Voce ja possui esse titulo / quantidade indisponivel");
                                }
                            } else {
                                messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                                messageLabel.setText("Digite um número da lista");
                            }
                        }
                    }
                }
            }
        });
    }
}
