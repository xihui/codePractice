package websocket.echo;

public class PVValue {
	double timeStamp;
	Object value;
	
	
	
	public PVValue(double timeStamp, Object value) {
		super();
		this.timeStamp = timeStamp;
		this.value = value;
	}
	public double getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(double timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
