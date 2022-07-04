package org.shoppingCart.cart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CartView {

    private Parent view;

    public CartView() throws IOException {
        URL uiFile = new File("G:\\Java\\AppCoff\\src\\main\\java\\org\\shoppingCart\\cart\\cart.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(uiFile);

        this.view = root;
    }

    public Parent getView() {
        return this.view;
    }


}
