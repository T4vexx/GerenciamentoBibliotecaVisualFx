package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import com.otavio.biblioteca.individuos.Alunos;
import com.otavio.exceptions.InvalidInformationsError;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class AdicionarItemController implements Initializable {

    @FXML
    private TextField titulo;
    @FXML
    private TextField autor;
    @FXML
    private TextField anoPublicacao;
    @FXML
    private TextField quantidaDisponivel;
    @FXML
    private TextField opc1;
    @FXML
    private TextField opc2;
    @FXML
    private TextField tipo;
    @FXML
    private Button adicionar;
    @FXML
    private Label info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
        if(displayBiblioteca.getMeuCliente() instanceof Alunos) {
            adicionar.setDisable(true);
            info.setText("Apenas professores e assessores podem adicionar itens");
            info.setTextFill(Paint.valueOf("#E53E3E"));
        }

        adicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!titulo.getText().equals("") && !autor.getText().equals("") && !anoPublicacao.getText().equals("") && !quantidaDisponivel.getText().equals("") && !opc1.getText().equals("") && !opc2.getText().equals("") && !tipo.getText().equals("")) {
                    if(tipo.getText().equalsIgnoreCase("CD") || tipo.getText().equalsIgnoreCase("Revista") || tipo.getText().equalsIgnoreCase("Livro")) {
                        try {
                            displayBiblioteca.adicionarItem(actionEvent,titulo.getText(),autor.getText(),anoPublicacao.getText(),quantidaDisponivel.getText(),opc1.getText(),opc2.getText(),tipo.getText());
                            info.setText("Item adicionado com sucesso");
                            info.setTextFill(Paint.valueOf("#38A169"));
                        } catch (InvalidInformationsError ex) {
                            info.setText(ex.getMessage());
                            info.setTextFill(Paint.valueOf("#E53E3E"));
                        }
                    } else {
                        info.setText("Apenas CD/Revista/Livro!");
                        info.setTextFill(Paint.valueOf("#E53E3E"));
                    }
                } else {
                    info.setText("Preencha todas as informações!");
                    info.setTextFill(Paint.valueOf("#E53E3E"));
                }
            }
        });
    }
}
