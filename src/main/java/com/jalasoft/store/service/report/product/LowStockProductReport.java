package com.jalasoft.store.service.report.product;

import com.jalasoft.store.model.Product;

public class LowStockProductReport extends StockProductReport {

    @Override
    public int getMinStock() {
        return 0;
    }

    @Override
    public int getMaxStock() {
        return Product.MINIMUM_STOCK_AMOUNT;
    }
}
