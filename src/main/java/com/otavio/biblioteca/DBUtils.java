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

/**
 * DBUtils 
 * Classe que armazena uma instancia estatica de DisplayBiblioteca para os controllers do visual conseguirem usar a mesma.
 * Ajuda na mudança de cena do visual.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class DBUtils {
    private static DisplayBiblioteca getDisplayBiblioteca = new DisplayBiblioteca();

    public static DisplayBiblioteca getDisplayBiblioteca() {
        return getDisplayBiblioteca;
    }


    /**
     * Método que faz a mudança de cena entre paginas javafx.
     * 
     * @param event um envento ActionEvent.
     * @param fxmlFile um nome de arquivo .fxml String.
     * @param title um titulo pra pagina String.
     * @param util uma string auxiliar.
     */
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

    /**
     * Método que faz a mudança de cena entre paginas de login e register javafx.
     * 
     * @param event Um envento ActionEvent.
     * @param fxmlFile Um nome de arquivo .fxml String.
     * @param title Um titulo pra pagina String.
     * @param nome Um nome de um usuario String.
     * @param matricula Uma matricula de um usuario string.
     * @param funcao A função de um usuario string.
     */
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
