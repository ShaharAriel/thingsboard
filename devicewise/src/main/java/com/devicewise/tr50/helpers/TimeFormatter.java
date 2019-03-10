package com.devicewise.tr50.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(RFC3339);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
