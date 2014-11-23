package com.uncc.sem1.ssdi.hma.monitoring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
	static{
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			ACTIVE_TILL = format.parse("9999/12/31 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not init class.", e);
		}
	}
	public static final Date ACTIVE_TILL ;
}
