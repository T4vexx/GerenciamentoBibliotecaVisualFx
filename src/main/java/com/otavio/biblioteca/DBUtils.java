package com.otavio.biblioteca;

import com.otavio.HelloApplication;
import com.otavio.controllers.RegisterAlunoController;
import com.otavio.controllers.RegisterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {
    private static DisplayBiblioteca getDisplayBiblioteca = new DisplayBiblioteca();

    public static DisplayBiblioteca getDisplayBiblioteca() {
        return getDisplayBiblioteca;
    }
    public static void changeScene(ActionEvent event, String fxmlFile, String title,String util) {
        Parent root = null;

        try {
            if (util == "vazio") {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
                root = loader.load();
            } else {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
                RegisterController twoStepRegister = loader.getController();
                twoStepRegister.setErrorLabel(util);
                root = loader.load();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }
}
