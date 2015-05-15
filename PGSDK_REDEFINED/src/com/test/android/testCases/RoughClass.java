package com.test.android.testCases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RoughClass {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
//		String s = "Updated key : total_session_duration category : timings value : 122";
//		System.out.println(s.substring(64));
		long time = System.currentTimeMillis();
		System.out.println(time);
		System.out.println(TimeUnit.MILLISECONDS.toSeconds(time));
		`
		long time2 = System.currentTimeMillis();
		System.out.println(time2);
		System.out.println(TimeUnit.MILLISECONDS.toSeconds(time2));

	}

}
