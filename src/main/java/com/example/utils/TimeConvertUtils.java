package com.example.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConvertUtils {
    public static String convertTimestampToDate (Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(timestamp);
    }

    public static Timestamp convertDateToTimestamp (String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) dateFormat.parse(dateString);
        return new Timestamp(date.getTime());
    }
}
