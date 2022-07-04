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

    public void addProduct(String productName) {
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if(productEntry!=null) {
            productEntry.increaseQuantity();
        } else {
            Product product = Product.valueOf(productName);
            CartEntry entry = new CartEntry(product,1);
            entries.put(productName.toUpperCase(),entry);
        }
    }

    public void removeProduct(String productName) {
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if (productEntry != null) {
            productEntry.decreaseQuantity();
        }
    }

    public int getQuantity(String productName) {
        CartEntry entry = entries.get(productName.toUpperCase());
        if (entry != null) {
            return entry.getQuantity();
        }
        return 0;
    }

    public float calculateTotal() {
        float total = 0;
        for(CartEntry entry : entries.values()) {
            float entryCost = entry.getProduct().getPrice()*entry.getQuantity();
            total = total + entryCost;
        }
        return total;
    }

    public List<CartEntry> getEntries() {
        return new ArrayList<>(entries.values());
    }

}
