module com.otavio.gerenciamentodebibliotecagui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens com.otavio to javafx.fxml;
    exports com.otavio;
    exports com.otavio.controllers;
    opens com.otavio.controllers to javafx.fxml;
}