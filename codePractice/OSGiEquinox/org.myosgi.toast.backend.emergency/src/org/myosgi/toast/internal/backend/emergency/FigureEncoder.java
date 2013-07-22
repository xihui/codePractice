package org.myosgi.toast.internal.backend.emergency;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class FigureEncoder implements Encoder.Text<Figure>{

	@Override
	public void destroy() {
		System.out.println("Destroy");
	}

	@Override
	public void init(EndpointConfig config) {
		System.out.println("init");
	}

	@Override
	public String encode(Figure object) throws EncodeException {
		return object.getJson().toString();
		
	}
	
	

}
