package com.jalasoft.store.service.notifications;

import com.jalasoft.store.model.Product;

public abstract class Observer {

    protected Product product;

    public abstract void notifyStockChange();
}
