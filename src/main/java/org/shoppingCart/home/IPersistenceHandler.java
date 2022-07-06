package org.shoppingCart.home;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IPersistenceHandler {

    public List<Product> getProducts() ;

    public boolean createProduct(Product product);
}