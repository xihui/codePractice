package test;

public class Sample {
	public long x;
	
	public void setX(long x) {
		this.x++;
		this.x = x;
	}
	
	public long getX() {
		x++;
		return x;
	}
}
