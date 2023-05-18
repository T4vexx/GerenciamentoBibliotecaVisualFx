package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import com.otavio.biblioteca.itens.Emprestimo;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DevolverItemController implements Initializable {

    @FXML
    private BorderPane listaEmprestimos;
    @FXML
    private Button devolver;
    @FXML
    private TextField nomeDoItem;
    @FXML
    private Label messageLabel;

    private ListView<String> listaEmprestimosView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
        ArrayList<Emprestimo> emps;
        int count = 0;
        emps = displayBiblioteca.getMeuCliente().getItensEmprestados();
        listaEmprestimosView = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        if(emps.size() > 0) {
            for(Emprestimo e: emps) {
                items.add("> "+count+" -> "+ (e.getDataDeDevolucaoReal() != null ? "Devolvido" : "Não Devolvido" )+" -> Item: "+e.getItem().getTitulo()+" | Data de emprestimo: "+e.getDataDeEmprestimo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))+" | Data prevista de devolução: "+e.getDataDeDevolucaoPrevista().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                count++;
            }
            listaEmprestimosView.setItems(items);
            listaEmprestimosView.setPrefWidth(443);
            listaEmprestimosView.setPrefHeight(275);
            listaEmprestimos.setCenter(listaEmprestimos);
        } else {
            devolver.setDisable(true);
        }

        devolver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Double value;
                if(!nomeDoItem.getText().equals("")) {
                    value = displayBiblioteca.devolverItem(nomeDoItem.getText());
                    if(value.equals(0.0)) {
                        messageLabel.setText("Item devolvido com sucesso | Você nao possui multas");
                        messageLabel.setTextFill(Paint.valueOf("#38A169"));
                    } else {
                        messageLabel.setText("Item devolvido com atraso | Você possui uma multa de R$ "+value);
                        messageLabel.setTextFill(Paint.valueOf("#ECC94B"));
                    }
                }
            }
        });
    }
}
