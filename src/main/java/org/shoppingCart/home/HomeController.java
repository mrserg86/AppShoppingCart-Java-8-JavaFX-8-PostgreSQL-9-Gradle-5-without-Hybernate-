package org.shoppingCart.home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.shoppingCart.cart.CartEntry;
import org.shoppingCart.cart.ShoppingCart;
import org.shoppingCart.persistence.PersistenceHandler;

public class HomeController {

    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    @FXML
    private GridPane productGridPane;

    @FXML
    public void initialize() {
        productGridPane.getChildren().clear();

        //for(Product product : persistenceHandler.getProducts()) {
            VBox productView1 = productView(persistenceHandler.getProducts().get(1));
            productGridPane.add(productView1, 0, 0);

            VBox productView2 = productView(persistenceHandler.getProducts().get(2));
            productGridPane.add(productView2, 1, 0);

            VBox productView3 = productView(persistenceHandler.getProducts().get(3));
            productGridPane.add(productView3, 2, 0);

            VBox productView4 = productView(persistenceHandler.getProducts().get(4));
            productGridPane.add(productView4, 0, 1);
        //}
    }

    private VBox productView(Product product) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        Label idOfPr = new Label(String.valueOf(product.getIdOfProduct()));
        Label productName = new Label(product.getProductName());
        Label cost = new Label(product.getProductCost() + " $");

        Button addButton = new Button("Add to cart");
        addButton.setUserData(product.getProductName());
        addButton.setOnAction (new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //add product to shopping cart
                Node sourceComponent = (Node)actionEvent.getSource();
                String productName = (String)sourceComponent.getUserData();
                ShoppingCart shoppingCart = ShoppingCart.getInstance();
                shoppingCart.addProduct(product.getIdOfProduct(),product.getProductName(),product.getProductCost());
            }
        });

        layout.getChildren().addAll(idOfPr,productName,cost,addButton);

        return layout;

    }

}
