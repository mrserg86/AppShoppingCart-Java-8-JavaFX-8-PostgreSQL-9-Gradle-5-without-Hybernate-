package org.shoppingCart;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        URL file = new File("G:\\Java\\AppCoff\\src\\main\\java\\org\\shoppingCart\\cartui.fxml").toURI().toURL();

        Parent root =FXMLLoader.load(file);

        stage.setTitle("Shopping cart");

        stage.setScene(new Scene(root, Color.TRANSPARENT));

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getWindow() {
        return window;
    }

}
