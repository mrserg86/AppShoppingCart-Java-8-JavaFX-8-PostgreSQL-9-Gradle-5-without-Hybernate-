package org.shoppingCart.cart;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CartController {

    @FXML
    private VBox cartPane;

    private Label totalPriceLabel;

    @FXML
    public void initialize() throws FileNotFoundException {
        // populate the view
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
                //Label productName = new Label(cartEntry.getProduct().name());
                //hBox.getChildren().add(productName);
                HBox productView = cartEntryView(cartEntry);
                cartPane.getChildren().add(productView);
            }

            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            cartPane.getChildren().add(separator);

            HBox totalView = totalView(ShoppingCart.getInstance().calculateTotal());
            cartPane.getChildren().add(totalView);

        }
    }

    private HBox totalView(float totalPrice) {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        Label totalLabel = new Label("Total : ");
        totalLabel.setStyle("-fx-font-size:15pt");

        this.totalPriceLabel = new Label(String.valueOf(totalPrice));
        layout.getChildren().addAll(totalLabel,this.totalPriceLabel);
        return layout;
    }

    private HBox cartEntryView(CartEntry cartEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);

        FileInputStream input = new FileInputStream(
                "G:\\Java\\AppCoff\\src\\main\\resources\\"+cartEntry.getProduct().getImageFile());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Label productName = new Label(cartEntry.getProduct().name());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");

        Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
        quantity.setStyle("-fx-padding:5px");

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-padding:5px");
        plusButton.setUserData(cartEntry.getProduct().name());
        plusButton.setOnAction( e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            ShoppingCart.getInstance().addProduct(name);
            quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculateTotal()));
        });

        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-padding:5px");
        minusButton.setUserData(cartEntry.getProduct().name());
        minusButton.setOnAction( e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            ShoppingCart.getInstance().removeProduct(name);
            quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculateTotal()));
        });

        Label price = new Label(String.valueOf("$ " + cartEntry.getProduct().getPrice()));
        price.setStyle("-fx-padding:5px");

        layout.getChildren().addAll(imageView,productName,plusButton,quantity,price,minusButton);

        return layout;
    }

}
