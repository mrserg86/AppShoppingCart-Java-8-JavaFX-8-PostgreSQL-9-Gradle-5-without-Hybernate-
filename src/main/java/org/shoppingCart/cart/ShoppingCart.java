package org.shoppingCart.cart;

import org.shoppingCart.home.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private static ShoppingCart INSTANCE;

    public static ShoppingCart getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShoppingCart();
        }
        return INSTANCE;
    }

    private Map<String, CartEntry> entries;

    private ShoppingCart() {
        this.entries = new HashMap<>();
    }

    public void addProduct(int idOfProduct, String productName, int productCost) {
        CartEntry productEntry = entries.get(productName);  //не по id, т.к. id нулом не может быть, а далее в if проверка по нулу
        if(productEntry!=null) {
            productEntry.increaseQuantity();
        } else {
            Product product = new Product(idOfProduct, productName, productCost);
            CartEntry entry = new CartEntry(product,1);
            entries.put(productName,entry);
        }
    }

    public void removeProduct(String productName) {
        CartEntry productEntry = entries.get(productName);
        if (productEntry != null) {
            productEntry.decreaseQuantity();
        }
    }

    public int getQuantity(int idOfProduct, String productName, int productCost) {
        CartEntry entry = entries.get(productName);
        if (entry != null) {
            return entry.getQuantity();
        }
        return 0;
    }

    public int calculateTotal() {
        int total = 0;
        for(CartEntry entry : entries.values()) {
            int entryCost = entry.getProduct().getProductCost()*entry.getQuantity();
            total = total + entryCost;
        }
        return total;
    }

    public List<CartEntry> getEntries() {
        return new ArrayList<>(entries.values());
    }

}
