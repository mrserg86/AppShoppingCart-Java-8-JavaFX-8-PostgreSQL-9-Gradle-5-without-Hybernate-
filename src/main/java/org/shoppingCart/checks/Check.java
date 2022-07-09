package org.shoppingCart.checks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Check {

    private int id;          // для checklines и checks

    private int idOfProduct;        //для checklines
    private int lineNumber;
    private int quantityOfProduct;
    private int summByLine;

    private LocalDate date;              //для checks
    private LocalTime time;
    private int summ;

    public Check(int id, int idOfProduct, int lineNumber, int quantityOfProduct, int summByLine) {
        this.id = id;
        this.idOfProduct = idOfProduct;
        this.lineNumber = lineNumber;
        this.quantityOfProduct = quantityOfProduct;
        this.summByLine = summByLine;
    }

    public Check(Integer id, LocalDate date, LocalTime time, int summ) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.summ = summ;
    }

    public int getIdOfCheck() {
        return id;
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
