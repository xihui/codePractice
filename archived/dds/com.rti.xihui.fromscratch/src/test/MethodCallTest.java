package test;

import java.util.Arrays;

public class MethodCallTest {
	
	public static void main(String[] args) {
		long n=1000000000L;
	
		Sample s =new Sample();
//		int[] array = new int[n/100];
//		Arrays.fill(array, 12);
		
		long start = System.currentTimeMillis();
	
		for(long i=0; i<n; i++){
			s.x=i;
			i = s.x;			
		}
		System.out.println("public member access:" + (System.currentTimeMillis()-start));
		long r;
		start = System.currentTimeMillis();		
		for(long i=0; i<n; i++){
			s.setX(i);
			r = s.getX();			
		}
		System.out.println("public method access:" + (System.currentTimeMillis()-start));
		
	}
	

}
