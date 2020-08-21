package com.jalasoft.store.model;

import java.util.Calendar;
import java.util.Date;

public class Transaction {

    private Product product;
    private TransactionType type;
    private int quantity;
    private Date date;

    public Transaction(Product product, TransactionType type, int quantity) {
        this.product = product;
        this.type = type;
        this.quantity = quantity;
    }

    public void execute() {
        updateProductStock();
        this.date = Calendar.getInstance().getTime();
    }

    public void execute(Date date) {
        updateProductStock();
        this.date = date;
    }

    private void updateProductStock() {
        if (TransactionType.SALE.equals(type)) {
            product.decreaseStock(quantity);
        } else if (TransactionType.PURCHASE.equals(type)) {
            product.increaseStock(quantity);
        }
    }

    public Product getProduct() {
        return product;
    }

    public TransactionType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

}
