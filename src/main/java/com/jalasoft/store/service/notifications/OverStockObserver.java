package com.jalasoft.store.service.notifications;

import com.jalasoft.store.model.Product;
import com.jalasoft.store.service.logger.StoreLogger;

public class OverStockObserver extends Observer {

    private static final StoreLogger LOGGER = StoreLogger.getInstance();

    public OverStockObserver(Product product) {
        this.product = product;
        this.product.addObserver(this);
    }

    @Override
    public void notifyStockChange() {
        if (product.getStock() > Product.MAXIMUM_STOCK_AMOUNT) {
            LOGGER.warning(String.format("OVER STOCK - %s", product));
        }
    }
}
