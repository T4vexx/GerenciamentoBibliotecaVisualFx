package com.otavio.controllers;

import com.otavio.HelloApplication;
import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Label nome;
    @FXML
    private Label matricula;
    @FXML
    private Label funcao;
    @FXML
    private Button pegarItemEmprestado;
    @FXML
    private Button devolverItem;
    @FXML
    private Button buscarItem;
    @FXML
    private Button meusEmprestimos;
    @FXML
    private Button adicionarItem;
    @FXML
    private Button sair;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
        loadPage("pegar-emprestado");
        pegarItemEmprestado.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("pegar-emprestado");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        devolverItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("devolver-item");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        buscarItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("buscar-item");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        meusEmprestimos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("meus-emprestimos");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        });

        adicionarItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("adicionar-item");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayBiblioteca.sair(actionEvent);
            }
        });
    }

    private FXMLLoader loadPage(String page) {
        Parent root = null;
        FXMLLoader loader = null;

        try{
            loader = new FXMLLoader(HelloApplication.class.getResource(page+"-user-view.fxml"));
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bp.setCenter(root);
        return loader;
    }

    public void setContaInfos(String nome, String matricula, String funcao) {
        this.nome.setText(nome);
        this.matricula.setText(matricula);
        this.funcao.setText(funcao);
    }

}
