package com.jalasoft.store.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jalasoft.store.service.deserializer.StoreDeserializer;

import java.util.List;

@JsonDeserialize(using = StoreDeserializer.class)
public class Store {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private List<Product> products;
    private List<Transaction> transactions;

    public Store(List<Product> products, List<Transaction> transactions) {
        this.products = products;
        this.transactions = transactions;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
