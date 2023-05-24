package com.otavio.controllers;

import com.otavio.biblioteca.DBUtils;
import com.otavio.biblioteca.DisplayBiblioteca;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button login;
    @FXML
    private Button trocarRegister;
    @FXML
    private TextField funcao;
    @FXML
    private TextField matricula;
    @FXML
    private PasswordField password;
    @FXML
    private Label erroNoFomulario;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean isCredentialsCorrect;
                DisplayBiblioteca displayBiblioteca;
                displayBiblioteca = DBUtils.getDisplayBiblioteca();
                if(!funcao.getText().equals("") && !matricula.getText().equals("") && !password.getText().equals("")) {
                    if(funcao.getText().equals("aluno") || funcao.getText().equals("professor") || funcao.getText().equals("assessor")) {
                        isCredentialsCorrect = displayBiblioteca.login(actionEvent,funcao.getText(),matricula.getText(),password.getText());
                        if(!isCredentialsCorrect) {
                            erroNoFomulario.setText("Credencias não coincidentes");
                            erroNoFomulario.setTextFill(Paint.valueOf("#E53E3E"));
                        }
                    } else {
                        erroNoFomulario.setText("Digite um função valida | aluno/professor/cd");
                        erroNoFomulario.setTextFill(Paint.valueOf("#E53E3E"));
                    }
                } else {
                    erroNoFomulario.setText("Credenciais não podem ser vazias");
                    erroNoFomulario.setTextFill(Paint.valueOf("#E53E3E"));
                }
            }
        });

        trocarRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent,"register-view.fxml","Gerenciamento de biblioteca | Register page","vazio");
            }
        });
    }
}



