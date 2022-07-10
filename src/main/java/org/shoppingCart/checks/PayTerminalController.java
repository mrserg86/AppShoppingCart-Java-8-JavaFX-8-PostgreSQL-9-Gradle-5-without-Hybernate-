package org.shoppingCart.checks;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.shoppingCart.cart.ShoppingCart;
import org.shoppingCart.home.IPersistentHandler;
import org.shoppingCart.persistence.PersistentHandler;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.Integer.parseInt;

public class PayTerminalController {


    IPersistentHandler persistenceHandler = PersistentHandler.getInstance();

    @FXML
    private TextField payableAmount;

    @FXML
    public void checkRecording(MouseEvent mouseEvent) {

        if (parseInt(payableAmount.getText()) == ShoppingCart.getInstance().calculateTotal()){
            try {
                Check check = new Check(-1, LocalDate.now(), LocalTime.now(), parseInt(payableAmount.getText()));//changed signature of LocalDate.now(), LocalTime.now()
                if (persistenceHandler.createCheck(check)) {
                    System.out.println("Check inserted into database");
                    payableAmount.clear();
                } else {
                    System.out.println("Something went wrong");
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("payableAmount isn't equal to calculateTotal");
        }
    }
}
