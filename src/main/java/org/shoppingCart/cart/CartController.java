package org.shoppingCart.cart;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class CartController {

    @FXML
    private VBox cartPane;

    private Label totalPriceLabel;

    @FXML
    public void initialize() {
        List<CartEntry> entries = ShoppingCart.getInstance().getEntries();
        cartPane.getChildren().clear();

        if(entries.isEmpty()) {
            Label emptyLabel = new Label("Empty Cart");
            cartPane.getChildren().add(emptyLabel);
        } else {
            Label shoppingCartTitle = new Label ("Shopping Cart");
            cartPane.getChildren().add(shoppingCartTitle);

            for(CartEntry cartEntry : entries) {
                HBox hBox = new HBox();
                Label productName = new Label(cartEntry.getProduct().getProductName());
                hBox.getChildren().add(productName);
                HBox productView = cartEntryView(cartEntry);    //получаем layout для CartEntry
                cartPane.getChildren().add(productView);        //передаём в поле --> в cart.fxml на отображение
            }

            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            cartPane.getChildren().add(separator);

            HBox totalView = totalView(ShoppingCart.getInstance().calculateTotal());  //аналогично /\
            cartPane.getChildren().add(totalView);

        }
    }

    private HBox totalView(int totalPrice) {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        Label totalLabel = new Label("Total : ");
        totalLabel.setStyle("-fx-font-size:15pt");

        this.totalPriceLabel = new Label(String.valueOf(totalPrice));
        layout.getChildren().addAll(totalLabel,this.totalPriceLabel);
        return layout;
    }

    private HBox cartEntryView(CartEntry cartEntry) {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);

        Label productName = new Label(cartEntry.getProduct().getProductName());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");

        Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
        quantity.setStyle("-fx-padding:5px");

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-padding:5px");
        plusButton.setUserData(cartEntry.getProduct().getProductName());
        plusButton.setOnAction( e -> {
            String name = (String) ((Node) e.getSource()).getUserData();

            ShoppingCart.getInstance().addProduct(cartEntry.getProduct().getIdOfProduct(),      //
                    cartEntry.getProduct().getProductName(), cartEntry.getProduct().getProductCost());

            quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(cartEntry.getProduct().getIdOfProduct(),
                    cartEntry.getProduct().getProductName(), cartEntry.getProduct().getProductCost())));

            this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculateTotal()));
        });

        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-padding:5px");
        minusButton.setUserData(cartEntry.getProduct().getProductName());
        minusButton.setOnAction( e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            ShoppingCart.getInstance().removeProduct(cartEntry.getProduct().getIdOfProduct(),   //
                    cartEntry.getProduct().getProductName(), cartEntry.getProduct().getProductCost());
            quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(cartEntry.getProduct().getIdOfProduct(),   //
                    cartEntry.getProduct().getProductName(), cartEntry.getProduct().getProductCost())));
            this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculateTotal()));
        });

        Label price = new Label(String.valueOf("$ " + cartEntry.getProduct().getProductCost()));
        price.setStyle("-fx-padding:5px");

        layout.getChildren().addAll(productName,plusButton,quantity,price,minusButton);

        return layout;
    }

}
