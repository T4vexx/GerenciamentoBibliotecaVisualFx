package com.otavio.controllers;

import com.otavio.HelloApplication;
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
    private Button proximo;
    @FXML
    private BorderPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                root = loadPage("register-2step");
                try {
                    if(nome.getText() != "" && matricula.getText() != "" && password.getText() != "" && funcao1.getText() != "") {
                        RegisterUsuarioController twoStepRegister = root.getController();
                        twoStepRegister.setFirstStepRegisterInfos(nome.getText(),matricula.getText(),password.getText(),funcao1.getText());
                    } else {
                        errorLabel.setText("Todas as informações devem ser prenchidas");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
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
