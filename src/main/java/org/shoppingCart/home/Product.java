package org.shoppingCart.home;

public class Product {

    private int idOfProduct;
    private String productName;
    private int productCost;

    public Product(int idOfProduct, String productName, int productCost) {
        this.idOfProduct = idOfProduct;
        this.productName = productName;
        this.productCost = productCost;
    }

    public int getIdOfProduct() {
        return idOfProduct;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductCost() {
        return productCost;
    }
}
