package com.jalasoft.store;

import com.jalasoft.store.model.Store;
import com.jalasoft.store.service.data.reader.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReadJsonFileTest {

    private final static String NON_EXISTENT_JSON_FILE_PATH = "invalid/route/path/file.json";
    private final static String EMPTY_JSON_FILE_PATH = "src/main/resources/static/test/emptyFile.json";
    private final static String PRODUCTS_ONLY_JSON_FILE_PATH = "src/main/resources/static/test/productsOnlyData.json";
    private final static String PRODUCTS_AND_TRANSACTION_JSON_FILE_PATH = "src/main/resources/static/test/fullData.json";

    @Test
    public void readNonExistentJson() {
        assertThrows(IOException.class,
                () -> {
                    JsonReader jsonReader = new JsonReader(NON_EXISTENT_JSON_FILE_PATH);
                    Store store = jsonReader.doRead();
                });
    }

    @Test
    public void readEmptyJson() {
        assertThrows(IOException.class,
                () -> {
                    JsonReader jsonReader = new JsonReader(EMPTY_JSON_FILE_PATH);
                    Store store = jsonReader.doRead();
                });
    }

    @Test
    public void readOnlyProductsDataJson() throws IOException {
        JsonReader jsonReader = new JsonReader(PRODUCTS_ONLY_JSON_FILE_PATH);
        Store store = jsonReader.doRead();
        assertNotNull(store);
        assertNotNull(store.getProducts());
        assertNotEquals(store.getProducts().size(), 0);
        assertNotNull(store.getTransactions());
        assertEquals(store.getTransactions().size(), 0);
    }

    @Test
    public void readFullDataJson() throws IOException {
        JsonReader jsonReader = new JsonReader(PRODUCTS_AND_TRANSACTION_JSON_FILE_PATH);
        Store store = jsonReader.doRead();
        assertNotNull(store);
        assertNotNull(store.getProducts());
        assertNotEquals(store.getProducts().size(), 0);
        assertNotNull(store.getTransactions());
        assertNotEquals(store.getTransactions().size(), 0);
    }
}
