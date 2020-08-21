package com.jalasoft.store.service.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jalasoft.store.model.Product;
import com.jalasoft.store.model.Store;
import com.jalasoft.store.model.Transaction;
import com.jalasoft.store.model.TransactionType;
import com.jalasoft.store.service.notifications.LowStockObserver;
import com.jalasoft.store.service.notifications.OverStockObserver;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StoreDeserializer extends StdDeserializer<Store> {

    // Json Collections
    private static final String PRODUCTS = "products";
    private static final String TRANSACTIONS = "transactions";

    // Json Product attributes
    private static final String CODE = "code";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String STOCK = "stock";

    // Json Transaction attributes
    private static final String PRODUCT_CODE = "productCode";
    private static final String TYPE = "type";
    private static final String QUANTITY = "quantity";
    private static final String DATE = "date";

    public StoreDeserializer() {
        this(null);
    }

    public StoreDeserializer(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public Store deserialize(JsonParser jp, DeserializationContext context)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Map<String, Product> products = new HashMap<>();
        if (node.get(PRODUCTS) != null) {
            products = deserializeProducts((ArrayNode) node.get(PRODUCTS));
        }
        List<Transaction> transactions = new ArrayList<>();
        if (node.get(TRANSACTIONS) != null) {
            transactions = deserializeTransactions(products, (ArrayNode) node.get(TRANSACTIONS));
        }

        return new Store(new ArrayList<>(products.values()), transactions);
    }

    private Map<String, Product> deserializeProducts(ArrayNode productsArray) {
        Map<String, Product> productsMap = new HashMap<>();
        for (JsonNode productJson : productsArray) {
            String code = productJson.get(CODE).asText();
            String name = productJson.get(NAME).asText();
            float price = productJson.get(PRICE).asLong();
            int stock = productJson.get(STOCK).asInt();

            Product product = new Product(code, name, price, stock);
            new LowStockObserver(product);
            new OverStockObserver(product);

            productsMap.put(code, product);
        }
        return productsMap;
    }

    private List<Transaction> deserializeTransactions(Map<String, Product> productsMap, ArrayNode transactionsArray) {
        List<Transaction> transactions = new ArrayList<>();
        for (JsonNode transactionJson : transactionsArray) {

            String productCode = transactionJson.get(PRODUCT_CODE).asText();
            Product partialProduct = productsMap.get(productCode);

            String typeAsString = transactionJson.get(TYPE).asText();
            TransactionType type = TransactionType.valueOf(typeAsString.toUpperCase());

            int quantity = transactionJson.get(QUANTITY).asInt();

            Date date = Calendar.getInstance().getTime();
            try {
                date = new SimpleDateFormat(Store.DATE_FORMAT).parse(transactionJson.get(DATE).asText());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Transaction transaction = new Transaction(partialProduct, type, quantity);
            transaction.execute(date);

            transactions.add(transaction);
        }
        return transactions;
    }
}
