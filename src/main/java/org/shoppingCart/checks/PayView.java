package org.shoppingCart.checks;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PayView {

    private Parent view;

    public PayView() throws IOException {
        URL url = new File("G:\\Java\\AppCoff\\src\\main\\java\\org\\shoppingCart\\checks\\payTerminal.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        this.view = root;
    }

    public Parent getView() {
        return view;
    }

}
