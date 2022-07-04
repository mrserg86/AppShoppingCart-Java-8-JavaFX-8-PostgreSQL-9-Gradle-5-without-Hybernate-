package org.shoppingCart.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HomeView {

    private Parent view;

    public HomeView() throws IOException {
        URL url = new File("G:\\Java\\AppCoff\\src\\main\\java\\org\\shoppingCart\\home\\home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        this.view = root;
    }

    public Parent getView() {
        return view;
    }

}
