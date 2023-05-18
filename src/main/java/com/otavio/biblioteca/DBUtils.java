package com.otavio.biblioteca;

import com.otavio.HelloApplication;
import com.otavio.controllers.RegisterAlunoController;
import com.otavio.controllers.RegisterController;
import com.otavio.controllers.UserController;
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
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            if (util.equals("vazio")) {
                root = loader.load();
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(title);
                stage.setScene(new Scene(root, 600,400));
                stage.show();
            } else {
                root = loader.load();
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(title);
                stage.setScene(new Scene(root, 600,400));
                stage.show();
                RegisterController registerController = loader.getController();
                registerController.setErrorLabel(util);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScene2(ActionEvent event, String fxmlFile, String title,String nome, String matricula, String funcao) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            root = loader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600,400));
            stage.show();
            UserController userController = loader.getController();
            userController.setContaInfos(nome,matricula,funcao);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
