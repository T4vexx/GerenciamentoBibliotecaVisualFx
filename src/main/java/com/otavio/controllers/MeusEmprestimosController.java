package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import com.otavio.biblioteca.itens.Emprestimo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MeusEmprestimosController implements Initializable {

    @FXML
    private BorderPane list;
    @FXML
    private Label aviso;
    private ListView<String> listaEmprestimos;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
        ArrayList<Emprestimo> emps;
        int count = 0;
        emps = displayBiblioteca.getMeuCliente().getItensEmprestados();
        listaEmprestimos = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        if(emps.size() > 0) {
            for(Emprestimo e: emps) {
                items.add("> "+count+" -> "+ (e.getDataDeDevolucaoReal() != null ? "Devolvido" : "Não Devolvido" )+" -> Item: "+e.getItem().getTitulo()+" | Data de emprestimo: "+e.getDataDeEmprestimo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))+" | Data prevista de devolução: "+e.getDataDeDevolucaoPrevista().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))+(e.getDataDeDevolucaoReal() != null ? " | Data de devolução real: "+e.getDataDeDevolucaoReal().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) : ""));
                count++;
            }
            listaEmprestimos.setItems(items);
            listaEmprestimos.setPrefWidth(443);
            listaEmprestimos.setPrefHeight(275);
            list.setCenter(listaEmprestimos);
        } else {
            aviso.setText("Você não tem itens emprestados aqui");
        }
    }
}
