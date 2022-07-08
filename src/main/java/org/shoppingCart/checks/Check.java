package org.shoppingCart.checks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Check {

    private int idOfCheck;          // для checklines и checks

    private int idOfProduct;        //для checklines
    private int lineNumber;
    private int quantityOfProduct;
    private int summByLine;

    private LocalDate date;              //для checks
    private LocalTime time;
    private int summ;

    public Check(int idOfCheck, int idOfProduct, int lineNumber, int quantityOfProduct, int summByLine) {
        this.idOfCheck = idOfCheck;
        this.idOfProduct = idOfProduct;
        this.lineNumber = lineNumber;
        this.quantityOfProduct = quantityOfProduct;
        this.summByLine = summByLine;
    }

    public Check(Integer idOfCheck, LocalDate date, LocalTime time, int summ) {
        this.idOfCheck = idOfCheck;
        this.date = date;
        this.time = time;
        this.summ = summ;
    }

    public int getIdOfCheck() {
        return idOfCheck;
    }

    public int getIdOfProduct() {
        return idOfProduct;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public int getSummByLine() {
        return summByLine;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getSumm() {
        return summ;
    }
}
