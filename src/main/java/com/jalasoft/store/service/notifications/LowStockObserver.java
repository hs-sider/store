package com.jalasoft.store.service.notifications;

import com.jalasoft.store.model.Product;
import com.jalasoft.store.service.logger.StoreLogger;

public class LowStockObserver extends Observer {

    private static final StoreLogger LOGGER = StoreLogger.getInstance();

    public LowStockObserver(Product product) {
        this.product = product;
        this.product.addObserver(this);
    }

    @Override
    public void notifyStockChange() {
        if (product.getStock() < Product.MINIMUM_STOCK_AMOUNT) {
            LOGGER.warning(String.format("LOW STOCK - %s", product));
        }
    }
}
