package com.jalasoft.store.service;

import com.jalasoft.store.model.Product;
import com.jalasoft.store.model.Store;
import com.jalasoft.store.model.Transaction;
import com.jalasoft.store.model.TransactionType;
import com.jalasoft.store.service.deserializer.IDataReader;
import com.jalasoft.store.service.deserializer.JsonReader;
import com.jalasoft.store.service.exception.BadInitialDataException;
import com.jalasoft.store.service.exception.InvalidProductCodeException;
import com.jalasoft.store.service.logger.StoreLogger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StoreService {

    private static final StoreLogger LOGGER = StoreLogger.getInstance();
    private static final String JSON_FILE_PATH = "src/main/resources/static/data.json";

    private Store store;

    public StoreService() throws BadInitialDataException {
        initStore();
    }

    private void initStore() throws BadInitialDataException {
        IDataReader reader = new JsonReader(JSON_FILE_PATH);
        try {
            store = reader.doRead();

            LOGGER.info("Store loaded successfully");
            for (Product product : store.getProducts()) {
                LOGGER.info(product.toString());
            }

        } catch (IOException e) {
            throw new BadInitialDataException();
        }
    }

    public Transaction registerTransactionSale(String productCode, int quantity) throws InvalidProductCodeException {
        return executeTransaction(productCode, quantity, TransactionType.SALE);
    }

    public Transaction registerTransactionPurchase(String productCode, int quantity) throws InvalidProductCodeException {
        return executeTransaction(productCode, quantity, TransactionType.PURCHASE);
    }

    private Transaction executeTransaction(String productCode, int quantity, TransactionType type) throws InvalidProductCodeException {
        Product product = findProductByCode(productCode);
        Transaction transaction = new Transaction(product, type, quantity);
        transaction.execute();

        store.addTransaction(transaction);
        LOGGER.info(String.format("Transaction executed successfully. %s", product));

        return transaction;
    }

    private Product findProductByCode(String productCode) throws InvalidProductCodeException {
        return store.getProducts().stream()
                .filter(productFilter -> productFilter.getCode().equals(productCode))
                .findAny()
                .orElseThrow(() -> new InvalidProductCodeException());
    }

    public Store getStore() {
        return store;
    }
}
