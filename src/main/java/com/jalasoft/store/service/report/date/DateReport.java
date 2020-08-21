package com.jalasoft.store.service.report.date;

import com.jalasoft.store.model.Store;
import com.jalasoft.store.model.Transaction;
import com.jalasoft.store.service.logger.StoreLogger;
import com.jalasoft.store.service.report.IReport;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DateReport implements IReport<Transaction> {

    private static final StoreLogger LOGGER = StoreLogger.getInstance();

    @Override
    public List<Transaction> generateReport(Store store) {

        LOGGER.info(String.format("Generating Report for transactions (Sales/Purchases) executed between %s and %s", getStartDate(), getEndDate()));
        List<Transaction> filteredTransactions = store.getTransactions().stream()
                .filter(transaction -> transaction.getDate().after(getStartDate()) && transaction.getDate().before(getEndDate()))
                .collect(Collectors.toList());
        LOGGER.info(String.format("Total transactions found: %d", filteredTransactions.size()));
        return filteredTransactions;
    }

    public abstract Date getStartDate();

    public abstract Date getEndDate();
}
