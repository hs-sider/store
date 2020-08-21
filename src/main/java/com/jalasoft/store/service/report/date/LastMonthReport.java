package com.jalasoft.store.service.report.date;

import java.util.Calendar;
import java.util.Date;

public class LastMonthReport extends DateReport {

    @Override
    public Date getStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    @Override
    public Date getEndDate() {
        return Calendar.getInstance().getTime();
    }
}
