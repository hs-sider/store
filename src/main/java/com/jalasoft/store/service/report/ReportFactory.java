package com.jalasoft.store.service.report;

import com.jalasoft.store.service.exception.ReportParametersException;
import com.jalasoft.store.service.report.date.LastMonthReport;
import com.jalasoft.store.service.report.date.LastWeekReport;
import com.jalasoft.store.service.report.product.LowStockProductReport;
import com.jalasoft.store.service.report.product.OverStockProductReport;

public class ReportFactory {

    public IReport<?> getReport(String type, String subType) throws ReportParametersException {

        if ("DATE".equalsIgnoreCase(type)) {
            if ("LASTWEEK".equalsIgnoreCase(subType)) {
                return new LastWeekReport();
            } else if ("LASTMONTH".equalsIgnoreCase(subType)) {
                return new LastMonthReport();
            }
        } else if ("PRODUCT".equalsIgnoreCase(type)) {
            if ("LOWSTOCK".equalsIgnoreCase(subType)) {
                return new LowStockProductReport();
            } else if ("OVERSTOCK".equalsIgnoreCase(subType)) {
                return new OverStockProductReport();
            }
        }

        throw new ReportParametersException();
    }
}
