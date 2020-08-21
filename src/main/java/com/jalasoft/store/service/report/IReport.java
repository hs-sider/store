package com.jalasoft.store.service.report;

import com.jalasoft.store.model.Store;

import java.util.List;

public interface IReport<T> {

    List<T> generateReport(Store store);
}
