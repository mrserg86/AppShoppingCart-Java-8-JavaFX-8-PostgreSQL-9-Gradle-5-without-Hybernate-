package org.shoppingCart.persistence;

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
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM goods");
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
    public boolean createProduct(Product product) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO goods (idOfProduct, productName, productCost) VALUES (?,?,?);");
            insertStatement.setInt(1, product.getIdOfProduct());
            insertStatement.setString(2, product.getProductName());
            insertStatement.setInt(3, product.getProductCost());

            insertStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

}
