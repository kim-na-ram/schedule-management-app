package com.bootcamp.schedulemanagementapp.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String timeStampToStringDate(Timestamp timestamp) {
        if (timestamp == null) {
            timestamp = new Timestamp(System.currentTimeMillis());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp.getTime());
        return simpleDateFormat.format(date);
    }
}