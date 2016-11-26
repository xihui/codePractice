package org.myosgi.toast.core.channel.sender;

import java.io.IOException;
import java.io.InputStream;

public interface IChannel {

	public InputStream send(ChannelMessage message) throws IOException;
	
}
