package org.shoppingCart.home;

import org.shoppingCart.checks.Check;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IPersistentHandler {

    public List<Product> getProducts(String text) ;

    boolean createCheck(Check check);

}