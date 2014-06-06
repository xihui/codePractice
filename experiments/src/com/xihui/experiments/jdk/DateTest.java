package com.xihui.experiments.jdk;

import java.util.Date;

public class DateTest {

	
	public static void main(String[] args) {
		Date date = new Date(Long.MAX_VALUE);
		System.out.println(date.toString());	
	}
}
