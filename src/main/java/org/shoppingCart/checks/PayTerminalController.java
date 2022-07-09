package org.shoppingCart.checks;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.shoppingCart.home.IPersistenceHandler;
import org.shoppingCart.persistence.PersistenceHandler;
import org.postgresql.util.PSQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class PayTerminalController {

        IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

        @FXML
        private TextField payableAmount;

        @FXML
        public void checkRecording(MouseEvent mouseEvent) {
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
        }
}
