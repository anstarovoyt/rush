package ru.naumen.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author stselovalnikov
 * @since Nov 1, 2013
 */
public class DateUtils {
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS0");
    static {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Yekaterinburg"));
    }
    
    public static String formatAsDate(long date) {
        return date <= 0 ? "" : DATE_FORMAT.format(new Date(date));
    }
}
