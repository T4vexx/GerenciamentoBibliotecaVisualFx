package com.otavio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * HelloApplication
 * Classe main do aplicativo de gerenciamento de biblioteca.
 * Nesse aplicativo temos todos os requisitos pedidos no trabalho do professore Lucas Ribas da UNESP/IBILCE na materia de POO.
 * Possui validação de entradas e tratativas de erros.
 * OBS: existe 3 lugares do código que precisa passar o diretorio do seu computador.
 * Sao eles:
 * HelloAplication.java - icone.
 * DisplayBiblioteca.java - diretorio do arquivo itens.txt
 * UsuariosDB.java - diretorio do arquivo usuarios.txt
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class HelloApplication extends Application {

    /**
     * Classe do JavaFx que inicia o aplicativo
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gerenciamento de biblioteca | Log-In page");
        stage.setResizable(false);
        stage.getIcons().add(new Image("C:"+File.separator+"Users"+File.separator+"tavin"+File.separator+"OneDrive"+File.separator+"Desktop"+File.separator+"3semestre"+File.separator+"GerenciamentoDeBibliotecaGUI"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"com"+File.separator+"otavio"+File.separator+"bibliotecaIcon.png"));
        //stage.getIcons().add(new Image("C:\\Users\\tavexx\\Desktop\\BibliotecaVisual\\src\\main\\resources\\com\\otavio\\bibliotecaIcon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}