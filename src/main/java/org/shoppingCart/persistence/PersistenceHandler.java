package org.shoppingCart.persistence;

import org.shoppingCart.cart.ShoppingCart;
import org.shoppingCart.checks.Check;
import org.shoppingCart.home.IPersistenceHandler;
import org.shoppingCart.home.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersistenceHandler implements IPersistenceHandler {
    private static PersistenceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "blogg_db";
    private String username = "postgres";
    private String password = "l275";
    private Connection connection = null;

    private PersistenceHandler() {
        initializePostgresqlDatabase();
    }

    public static PersistenceHandler getInstance() {
        if (instance == null) {
            instance = new PersistenceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("DB connected");
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) {
                System.exit(-1);
            }
        }
    }

    @Override
    public List<Product> getProducts() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cashtest.goods");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<Product> returnValues = new ArrayList<>();

            while (sqlReturnValues.next()) {
                returnValues.add(new Product(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3)));
            }
            return returnValues;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createCheck(Check check) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO cashtest.checks (idOfCheck, date, time, Summ) VALUES (?,?,?,?)");
            insertStatement.setInt(1, check.getIdOfCheck());
            insertStatement.setDate(2, Date.valueOf(check.getDate()));
            insertStatement.setTime(3, Time.valueOf(check.getTime()));
            insertStatement.setInt(4, check.getSumm());

            try {
                insertStatement.execute();
            } catch()
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

}
