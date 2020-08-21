package com.jalasoft.store.service.data.reader;

import com.jalasoft.store.model.Store;

import java.io.IOException;

public interface IDataReader {
    Store doRead() throws IOException;
}
