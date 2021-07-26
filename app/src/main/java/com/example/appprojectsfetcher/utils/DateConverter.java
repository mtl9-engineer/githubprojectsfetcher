package com.example.appprojectsfetcher.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConverter {

    private int days ;
    private Date date;
    public DateConverter() {
        this.date= new Date();
        this.days= 30;
    }

    public Date subtractDays() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);

        return cal.getTime();
    }

    public String formatDate(Date date ){
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dmyFormat.format(date);
    }
}
