package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
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

public class RegisterAlunoController implements Initializable {


    @FXML
    private TextField curso;
    @FXML
    private TextField periodo;
    @FXML
    private Button registrar;
    @FXML
    private Label erroNoFomulario;
    private String nome;
    private String matricula;
    private String senha;
    private String funcao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isCredentialsCorrect;
                DisplayBiblioteca displayBiblioteca;
                displayBiblioteca = DBUtils.getDisplayBiblioteca();

                if(curso.getText() != "" && periodo.getText() != "") {
                    isCredentialsCorrect = displayBiblioteca.register(event,nome,senha,matricula,curso.getText(),periodo.getText(),"aluno");
                    if(!isCredentialsCorrect) {
                        DBUtils.changeScene(event,"register-view.fxml","Gerenciamento de biblioteca | Register page","Matrícula já existente | Tente novamente");
                    } else {
                        erroNoFomulario.setTextFill(Paint.valueOf("#48BB78"));
                        erroNoFomulario.setText("Conta criada com sucesso");
                    }
                } else {
                    erroNoFomulario.setText("Credenciais não podem ser vazias");
                }
            }
        });
    }


    public void setFirstStepRegisterInfos(String nome,String matricula,String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        this.funcao = funcao;
    }
}
