package org.shoppingCart.home;

import org.shoppingCart.checks.Check;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IPersistenceHandler {

    public List<Product> getProducts() ;

    boolean createCheck(Check check);
}