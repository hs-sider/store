package com.jalasoft.store.service.deserializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalasoft.store.model.Store;

import java.io.File;
import java.io.IOException;

public class JsonReader implements IDataReader {

    private String jsonPathFile;

    public JsonReader(String jsonPathFile) {
        this.jsonPathFile = jsonPathFile;
    }

    @Override
    public Store doRead() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonPathFile), new TypeReference<Store>() {
        });
    }
}
