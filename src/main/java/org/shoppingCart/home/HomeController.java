package org.shoppingCart.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
        productGridPane.getChildren().clear();

        List<Product> productList = persistenceHandler.getProducts();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            VBox productView = productView(product);
            productGridPane.add(productView, i, 0);
        }

        productName.textProperty().addListener((observable, oldValue, newValue) -> {
        });


    }

    private VBox productView(Product product) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        Label idOfPr = new Label(String.valueOf(product.getIdOfProduct()));
        Label productName = new Label(product.getProductName());
        Label cost = new Label(product.getProductCost() + " $");

        Button addButton = new Button("Add to cart");
        addButton.setUserData(product.getProductName());
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //add product to shopping cart
                Node sourceComponent = (Node) actionEvent.getSource();
                String productName = (String) sourceComponent.getUserData();
                ShoppingCart shoppingCart = ShoppingCart.getInstance();
                shoppingCart.addProduct(product.getIdOfProduct(), product.getProductName(), product.getProductCost());
            }
        });

        layout.getChildren().addAll(idOfPr, productName, cost, addButton);

        return layout;

    }

}
