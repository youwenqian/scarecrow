package com.shoes.scarecrow.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
*	@author wangyc
*	2016
*/
public class DateUtils {
    private static final String DATE_FORMAT_24_HOUR = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_FORMAT_DAY = "yyyy-MM-dd";


	public static Calendar getUTCCalendar() {
		// 1.获取本地时间
		Calendar calendar = Calendar.getInstance();
		// 2.取得时间偏移量
		int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
		// 3.取得夏令时差
		int dstOffset = calendar.get(Calendar.DST_OFFSET);

		// 4.从本地时间扣除 偏移量和夏令时差 即可得到 UTC时间
		// java.util.Calendar.MILLISECOND 毫秒数
		calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		return calendar;
	}

	public static Date getUTCDate() {
		return getUTCCalendar().getTime();
	}

	public static Date getUTCDate(long utcTimeInMillis) {
		Calendar calendar = getUTCCalendar();
		calendar.setTimeInMillis(utcTimeInMillis);
		return calendar.getTime();
	}
	
    public static String getFormatedDateString(float timeZoneOffset){
        return getFormatedDateString(timeZoneOffset, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String getFormatedDateString(float timeZoneOffset, String pattern){
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        
        int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(newTime);
        if (ids.length == 0) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = new SimpleTimeZone(newTime, ids[0]);
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }

    public static String formatDate(Date date, String pattern){
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
       return sdf.parse(dateStr);
    }

    public static Date parse(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DAY);
        return sdf.parse(dateStr);
    }

}

