package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import com.otavio.biblioteca.itens.Emprestimo;
import com.otavio.exceptions.NotBorrowedItemError;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DevolverItemController implements Initializable {

    @FXML
    private BorderPane listar;
    @FXML
    private Button devolver;
    @FXML
    private TextField nomeDoItem;
    @FXML
    private Label messageLabel;
    private ListView<String> listaEmprestimosView;
    private final DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createList();

        devolver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Double value;
                if(!nomeDoItem.getText().equals("")) {
                    try {
                        value = displayBiblioteca.devolverItem(nomeDoItem.getText());
                        if(value.equals(0.0)) {
                            messageLabel.setText("Item devolvido com sucesso | Você nao possui multas");
                            messageLabel.setTextFill(Paint.valueOf("#38A169"));
                            createList();
                        } else {
                            messageLabel.setText("Item devolvido com atraso | Você possui uma multa de R$ "+value);
                            messageLabel.setTextFill(Paint.valueOf("#ECC94B"));
                            createList();
                        }
                    } catch (NotBorrowedItemError ex) {
                        messageLabel.setText(ex.getMessage());
                        messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                    }
                } else {
                    messageLabel.setText("Digite algum item!");
                    messageLabel.setTextFill(Paint.valueOf("#E53E3E"));
                }
            }
        });
    }

    private void createList() {
        ArrayList<Emprestimo> emps;
        int count = 0;
        emps = displayBiblioteca.getMeuCliente().getItensEmprestados();
        listaEmprestimosView = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        if(emps.size() > 0) {
            for(Emprestimo e: emps) {
                items.add("> "+count+" / "+ (e.getDataDeDevolucaoReal() != null ? "Devolvido" : "Não Devolvido" )+" / "+e.getItem().getTitulo()+" / Data de emprestimo: "+e.getDataDeEmprestimo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))+" / Data prevista de devolução: "+e.getDataDeDevolucaoPrevista().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + (e.getDataDeDevolucaoReal() != null ? " / Data de devolução real: "+e.getDataDeDevolucaoReal().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) : ""));
                count++;
            }
            listaEmprestimosView.setItems(items);
            listaEmprestimosView.setPrefWidth(443);
            listaEmprestimosView.setPrefHeight(275);
            listar.setCenter(listaEmprestimosView);
        } else {
            devolver.setDisable(true);
        }

        listaEmprestimosView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(listaEmprestimosView.getSelectionModel().getSelectedItem() != null) {
                    String[] campos = listaEmprestimosView.getSelectionModel().getSelectedItem().split(" / ");
                    nomeDoItem.setText(campos[2]);
                }
            }
        });
    }
}
