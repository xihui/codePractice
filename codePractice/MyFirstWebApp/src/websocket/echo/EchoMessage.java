/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package websocket.echo;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import com.fasterxml.jackson.databind.ObjectMapper;


public class EchoMessage extends WebSocketServlet {

    private static final long serialVersionUID = 1L;
    private volatile int byteBufSize;
    private volatile int charBufSize;

    
    public EchoMessage() {
    	System.out.println("A new WebSocket Servlet");
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        byteBufSize = getInitParameterIntValue("byteBufferMaxSize", 2097152);
        charBufSize = getInitParameterIntValue("charBufferMaxSize", 2097152);
    }

    public int getInitParameterIntValue(String name, int defaultValue) {
        String val = this.getInitParameter(name);
        int result;
        if(null != val) {
            try {
                result = Integer.parseInt(val);
            }catch (Exception x) {
                result = defaultValue;
            }
        } else {
            result = defaultValue;
        }

        return result;
    }



    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,
            HttpServletRequest request) {
    	System.out.println("new bounds from" + request.getRemoteHost());
        return new EchoMessageInbound(byteBufSize,charBufSize);
    }

    private static final class EchoMessageInbound extends MessageInbound {

    	private volatile boolean isClosed;
    
        public EchoMessageInbound(int byteBufferMaxSize, int charBufferMaxSize) {
            super();
            setByteBufferMaxSize(byteBufferMaxSize);
            setCharBufferMaxSize(charBufferMaxSize);
        	System.out.println("A new MessageInbound");

        }
        
        @Override
        protected void onOpen(final WsOutbound outbound) {
        	super.onOpen(outbound);
        	try {
				outbound.writeTextMessage(CharBuffer.wrap("Welcome!"));
				Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate
				(new Runnable() {
					
					@Override
					public void run() {
						try {
							if(!isClosed){
									ObjectMapper mapper = new ObjectMapper();
								PVValue value = new PVValue(Calendar.getInstance().getTimeInMillis(), createRandomArray(100));
								 outbound.writeTextMessage(
											CharBuffer.wrap(mapper.writeValueAsString(value)));
								
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 1000, 100, TimeUnit.MILLISECONDS);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        private double[] createRandomArray(int length){
        	double[] r = new double[length];
        	for(int i=0; i<length; i++)
        		r[i] = (Math.random()*100);
        	return r;
        }
        
        @Override
        protected void onClose(int status) {
        	super.onClose(status);
        	isClosed=true;
        	System.out.println("closed");
        	
        }

        @Override
        protected void onBinaryMessage(ByteBuffer message) throws IOException {
            getWsOutbound().writeBinaryMessage(message);
        }

        @Override
        protected void onTextMessage(CharBuffer message) throws IOException {
            getWsOutbound().writeTextMessage(message);
        }
    }
}
