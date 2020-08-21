package com.jalasoft.store.service.report.product;

import com.jalasoft.store.model.Product;

public class OverStockProductReport extends StockProductReport {

    @Override
    public int getMinStock() {
        return Product.MAXIMUM_STOCK_AMOUNT;
    }

    @Override
    public int getMaxStock() {
        return Integer.MAX_VALUE;
    }
}
