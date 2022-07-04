package org.shoppingCart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import org.shoppingCart.cart.CartView;
import org.shoppingCart.home.HomeView;

import java.io.IOException;

public class AppController {

    @FXML
    BorderPane contentPane;

    public void closeApp() {
        App.getWindow().close();
    }

    public void showHomeView() throws IOException {
        contentPane.setCenter(new HomeView().getView());
    }

    public void showCartView() throws IOException {
        contentPane.setCenter(new CartView().getView());
    }

}
