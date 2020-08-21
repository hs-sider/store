package com.jalasoft.store.service.report.product;

import com.jalasoft.store.model.Product;
import com.jalasoft.store.model.Store;
import com.jalasoft.store.service.logger.StoreLogger;
import com.jalasoft.store.service.report.IReport;

import java.util.List;
import java.util.stream.Collectors;

public abstract class StockProductReport implements IReport<Product> {

    StoreLogger LOGGER = StoreLogger.getInstance();

    @Override
    public List<Product> generateReport(Store store) {

        LOGGER.info(String.format("Generating Report for product's stock between %d and %d", getMinStock(), getMaxStock()));
        List<Product> filteredProducts = store.getProducts().stream()
                .filter(product -> product.getStock() > getMinStock() && product.getStock() < getMaxStock())
                .collect(Collectors.toList());
        LOGGER.info(String.format("Total products found: %d", filteredProducts.size()));
        return filteredProducts;
    }

    public abstract int getMinStock();

    public abstract int getMaxStock();
}
