package com.allianz.shopping.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtility {
	public static Date convertStringToDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dateLocal = sdf.parse(date);
			return dateLocal;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	public static java.sql.Date ConvertUtilToSql(java.util.Date date)
	{
		  java.sql.Date sDate = new java.sql.Date(date.getTime());
	        return sDate;
	}
	/*
	 * public static String convertDateToString(Date date) {
	 * 
	 * return datee; }
	 */

}
