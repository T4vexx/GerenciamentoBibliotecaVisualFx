package com.otavio.biblioteca;

import com.otavio.HelloApplication;
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
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }
}
