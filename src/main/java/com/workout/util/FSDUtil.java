package com.workout.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FSDUtil{ 
	public static final String DATE_DASH_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT = "dd/MM/yyyy"; 

public static String prepareYearMonthDateFromString( String dateStr ){
	

	String parsedDate="";
		try {
			Date  initDate = new SimpleDateFormat(DATE_DASH_FORMAT).parse(dateStr);
			  SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			     parsedDate = formatter.format(initDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  

	    return parsedDate;
	}
   
}
