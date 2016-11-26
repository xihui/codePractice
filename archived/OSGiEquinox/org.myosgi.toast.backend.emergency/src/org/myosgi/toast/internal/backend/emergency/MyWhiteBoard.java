package org.myosgi.toast.internal.backend.emergency;


import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**Example is from:
 * https://netbeans.org/kb/docs/javaee/maven-websocketapi.html
 *
 */
@ServerEndpoint(value="/whiteboardendpoint", encoders = {FigureEncoder.class}, decoders={FigureDecoder.class})
public class MyWhiteBoard {
	
	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnMessage
	public void broadcaseFigure(Figure figure, Session session) throws IOException, EncodeException{
		System.out.println("broadcastFigure: " + figure);
		for (Session peer : peers) {
			if(!peer.equals(session)){
				peer.getBasicRemote().sendObject(figure);
			}
		}
	}
	
	@OnOpen
	public void onOpen(Session peer){
		System.out.println("Joined: " + peer.toString());
		peers.add(peer);		
	}
	
	@OnClose
	public void onClose(Session peer){
		peers.remove(peer);
	}

}
