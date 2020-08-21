package com.jalasoft.store;

import com.jalasoft.store.service.report.IReport;
import com.jalasoft.store.service.report.ReportFactory;
import com.jalasoft.store.service.report.date.DateReport;
import com.jalasoft.store.service.report.date.LastMonthReport;
import com.jalasoft.store.service.report.date.LastWeekReport;
import com.jalasoft.store.service.report.product.LowStockProductReport;
import com.jalasoft.store.service.report.product.OverStockProductReport;
import com.jalasoft.store.service.report.product.StockProductReport;
import com.jalasoft.store.service.exception.ReportParametersException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportFactoryTest {

    ReportFactory reportFactory = new ReportFactory();

    @Test
    public void obtainLastWeekReportInstance() throws ReportParametersException {
        IReport<?> report = reportFactory.getReport("DATE", "LASTWEEK");
        assertTrue(report instanceof DateReport);
        assertTrue(report instanceof LastWeekReport);
    }

    @Test
    public void obtainLastMonthReportInstance() throws ReportParametersException {
        IReport<?> report = reportFactory.getReport("DATE", "LASTMONTH");
        assertTrue(report instanceof DateReport);
        assertTrue(report instanceof LastMonthReport);
    }

    @Test
    public void obtainLowStockInstance() throws ReportParametersException {
        IReport<?> report = reportFactory.getReport("PRODUCT", "LOWSTOCK");
        assertTrue(report instanceof StockProductReport);
        assertTrue(report instanceof LowStockProductReport);
    }

    @Test
    public void obtainOverStockInstance() throws ReportParametersException {
        IReport<?> report = reportFactory.getReport("PRODUCT", "OVERSTOCK");
        assertTrue(report instanceof StockProductReport);
        assertTrue(report instanceof OverStockProductReport);
    }

    @Test
    public void invalidType() {
        assertThrows(ReportParametersException.class,
                () -> {
                    IReport<?> report = reportFactory.getReport("INVALID_TYPE", "LASTWEEK");
                });
    }

    @Test
    public void invalidSubtype() {
        assertThrows(ReportParametersException.class,
                () -> {
                    IReport<?> report = reportFactory.getReport("PRODUCT", "INVALID_SUBTYPE");
                });
    }

}
