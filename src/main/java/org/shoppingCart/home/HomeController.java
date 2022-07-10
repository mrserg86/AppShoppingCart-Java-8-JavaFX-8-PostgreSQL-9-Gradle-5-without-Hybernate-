package org.shoppingCart.home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.shoppingCart.cart.ShoppingCart;
import org.shoppingCart.persistence.PersistentHandler;

import java.util.List;

public class HomeController {

    IPersistentHandler persistenceHandler = PersistentHandler.getInstance();

    @FXML
    private TextField productName;

    @FXML
    private GridPane productGridPane;

    @FXML
    public void initialize() {

        productName.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshProducts();
        });
        refreshProducts();

    }

    public void refreshProducts() {
        productGridPane.getChildren().clear();
    List<Product> productList = persistenceHandler.getProducts(productName.getText());
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            VBox productView = productView(product);
            productGridPane.add(productView, i, 0);
        }
    }

    private VBox productView(Product product) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        Label idOfPr = new Label(String.valueOf(product.getId()));
        Label productName = new Label(product.getName());
        Label cost = new Label(product.getCost() + " $");

        Button addButton = new Button("Add to cart");
        addButton.setUserData(product.getName());
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //add product to shopping cart
                Node sourceComponent = (Node) actionEvent.getSource();
                String productName = (String) sourceComponent.getUserData();
                ShoppingCart shoppingCart = ShoppingCart.getInstance();
                shoppingCart.addProduct(product.getId(), product.getName(), product.getCost());
            }
        });

        layout.getChildren().addAll(idOfPr, productName, cost, addButton);

        return layout;

    }

}
