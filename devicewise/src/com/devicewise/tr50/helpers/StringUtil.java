package com.devicewise.tr50.helpers;

public class StringUtil {

	public static String trim(String source,int numChars){
		if(numChars>=source.length())
			return source;
		return(source.substring(0, numChars));
	}
}
