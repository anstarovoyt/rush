package ru.naumen.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author stselovalnikov
 * @since Nov 1, 2013
 */
public class DateUtils
{
	/**
	 * Default timezone for app
	 */
    public static final String ASIA_YEKATERINBURG_TIMEZONE = "Asia/Yekaterinburg";

	/**
	 * Formatting for rating info
	 */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS0");

    static
    {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(ASIA_YEKATERINBURG_TIMEZONE));
    }

    public synchronized static String formatLongDateAsFormattedDateString(long date)
    {
        return date <= 0 ? "" : DATE_FORMAT.format(new Date(date));
    }
}
