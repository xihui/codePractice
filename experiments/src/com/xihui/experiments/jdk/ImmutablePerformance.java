package com.xihui.experiments.jdk;

public class ImmutablePerformance {
	
	public static final class Sample {

		private int a;
		
		private long b;

		public Sample(int a, long b) {
			super();
			this.a = a;
			this.b = b;
		}
		
		public int getA() {
			return a;
		}
		
		public long getB() {
			return b;
		}
		
		public void setA(int a) {
			this.a = a;
		}
		
		public void setB(long b) {
			this.b = b;
		}
	}
	
	
	public static void main(String[] args) {
	
		int n = 1000000000;
		long start = System.nanoTime();
		Sample[] samples = new Sample[1000];
		int i=0;
		while(i<n){
			samples[i%1000] = new Sample(i, i*10);
			i++;
		}
		System.out.println("Immutable: " + (System.nanoTime() - start)/1000000 + "ms");
		System.out.println(samples[12].getA());
		
		start = System.nanoTime();
		Sample[] samples2 = new Sample[1000];
		for(int k=0; k<1000; k++){
			samples2[k] = new Sample(0, 0);
		}
		i=0;
		while(i<n){
			samples2[i%1000].setA(i);
			samples2[i%1000].setB(i*10);
			i++;
		}
		System.out.println("Mutable: " + (System.nanoTime() - start)/1000000 + "ms");
		System.out.println(samples[12].getA());
	}
	
}
