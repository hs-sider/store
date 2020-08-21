package com.jalasoft.store.model;

import com.jalasoft.store.service.notifications.Observable;
import com.jalasoft.store.service.notifications.Observer;

import java.util.ArrayList;
import java.util.List;

public class Product implements Observable {

    public static final int MINIMUM_STOCK_AMOUNT = 10;
    public static final int MAXIMUM_STOCK_AMOUNT = 100;

    private String code;
    private String name;
    private float price;
    private int stock;

    private List<Observer> observers = new ArrayList<>();

    public Product(String code, String name, float price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void increaseStock(int amount) {
        this.stock += amount;
        notifyToObservers();
    }

    public void decreaseStock(int amount) {
        this.stock -= amount;
        notifyToObservers();
    }

    @Override
    public String toString() {
        return String.format("Product Code: %s, Product Name: %s, Price: %.2f (Stock: %d)", code, name, price, stock);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyToObservers() {
        observers.forEach(observable -> observable.notifyStockChange());
    }
}
