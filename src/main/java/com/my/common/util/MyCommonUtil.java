package com.my.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyCommonUtil {
	public static String getOneWeekAgoDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date date = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(date);
	}
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(date);
	}
	public static int getCurrDateSeq() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	public static int getCurrMin() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}
}
