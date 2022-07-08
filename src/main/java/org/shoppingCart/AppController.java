package org.shoppingCart;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.shoppingCart.cart.CartView;
import org.shoppingCart.checks.PayView;
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

    public void showPayView() throws IOException {
        contentPane.setCenter(new PayView().getView());
    }

}
