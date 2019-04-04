package com.devicewise.tr50.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeFormatter {
	
	private static SimpleDateFormat rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public static String toRFC3339(Date date)
	{
	   return rfc3339.format(date).replaceAll("(\\d\\d)(\\d\\d)$", "$1:$2");
	}

	public static String toRFC3339(String date)
	{
	   return rfc3339.format(date).replaceAll("(\\d\\d)(\\d\\d)$", "$1:$2");
	}
	
	public static Date toDate(String RFC3339)
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			return sdf.parse(RFC3339);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}
}
