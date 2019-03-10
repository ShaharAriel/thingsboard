package com.devicewise.tr50.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper{

	public static String toString(String filename) throws IOException{

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String         line = null;
		String         ls = System.getProperty("line.separator");
		StringBuilder  sb = new StringBuilder();

		while((line = reader.readLine()) != null) {
			sb.append( line );
			sb.append( ls );
		}
		
		reader.close();
		return sb.toString();}
}
