package com.otavio.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUsuarioController implements Initializable {

    @FXML
    private Label test;
    private String nome;
    private String matricula;
    private String senha;
    private String funcao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setFirstStepRegisterInfos(String nome,String matricula,String senha,String funcao) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        this.funcao = funcao;
        test.setText(nome+" "+matricula+" "+senha+" "+funcao);
    }
}
