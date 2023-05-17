package com.otavio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gerenciamento de biblioteca | Log-In page");
        stage.setResizable(false);
        //stage.getIcons().add(new Image("C:"+File.separator+"Users"+File.separator+"tavin"+File.separator+"OneDrive"+File.separator+"Desktop"+File.separator+"3semestre"+File.separator+"GerenciamentoDeBibliotecaGUI"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"com"+File.separator+"otavio"+File.separator+"bibliotecaIcon.png"));
        stage.getIcons().add(new Image("C:\\Users\\tavexx\\Desktop\\BibliotecaVisual\\src\\main\\resources\\com\\otavio\\bibliotecaIcon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}