package com.otavio.controllers;

import com.otavio.HelloApplication;
import com.otavio.biblioteca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField nome;
    @FXML
    private TextField matricula;
    @FXML
    private PasswordField password;
    @FXML
    private TextField funcao1;
    @FXML
    private Label errorLabel;
    @FXML
    private Button voltar;
    @FXML
    private Button proximo;
    @FXML
    private BorderPane pane;
    private String error = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(!error.equalsIgnoreCase("")) {
            errorLabel.setText(error);
        }

        proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    if(nome.getText() != "" && matricula.getText() != "" && password.getText() != "" && funcao1.getText() != "") {
                        if(funcao1.getText().equalsIgnoreCase("aluno")) {
                            root = loadPage("register-aluno");
                            RegisterAlunoController twoStepRegister = root.getController();
                            twoStepRegister.setFirstStepRegisterInfos(nome.getText(),matricula.getText(),password.getText());
                        } else if(funcao1.getText().equalsIgnoreCase("professor")) {

                        } else if(funcao1.getText().equalsIgnoreCase("assessor")) {

                        }
                    } else {
                        errorLabel.setText("Todas as informações devem ser prenchidas");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"login-view.fxml","Gerenciamento de biblioteca | Log-In page","vazio");
            }
        });
    }

    public void setErrorLabel(String error) {
        this.error = error;
    }

    private FXMLLoader loadPage(String page) {
        Parent root = null;
        FXMLLoader loader = null;

        try{
            loader = new FXMLLoader(HelloApplication.class.getResource(page+"-view.fxml"));
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pane.setCenter(root);
        return loader;
    }
}
