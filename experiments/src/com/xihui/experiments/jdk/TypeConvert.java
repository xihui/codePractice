package com.xihui.experiments.jdk;

public class TypeConvert {
	
	public static void main(String[] args) {
		int i = 123456;
		float f = 11212123.23232221212123232f;
		double di = (double)i;
		double df = (double)f;
		long l = (long)i;
		double dl=(double)l;
		int i2 = (int)di;
		int i3= (int)dl;
		float f2=(float)df;
		System.out.println(i==i2);
		System.out.println(i==i3);		
		System.out.println(f2==f);
		//verified that type coerce doesn't change value
	}

}
