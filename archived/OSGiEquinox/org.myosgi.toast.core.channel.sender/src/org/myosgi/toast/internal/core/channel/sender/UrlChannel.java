/*******************************************************************************
 * Copyright (c) 2009 Paul VanderLei, Simon Archer, Jeff McAffer and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Toast is an Equinox/OSGi system developed for the book Eclipse, Equinox and
 * OSGi - Creating Highly Modular Java Applications See http://equinoxosgi.org
 * 
 * Contributors: Paul VanderLei, Simon Archer and Jeff McAffer - initial API and
 * implementation
 *******************************************************************************/
package org.myosgi.toast.internal.core.channel.sender;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.myosgi.toast.core.ICoreConstants;
import org.myosgi.toast.core.LogUtility;
import org.myosgi.toast.core.PropertyManager;
import org.myosgi.toast.core.UrlBuilder;
import org.myosgi.toast.core.channel.sender.ChannelMessage;
import org.myosgi.toast.core.channel.sender.IChannel;

public class UrlChannel implements IChannel {
	private String urlSpec;

	public UrlChannel() {
		super();
		urlSpec = PropertyManager.getProperty(
				ICoreConstants.BACK_END_URL_PROPERTY,
				ICoreConstants.BACK_END_URL_DEFAULT);
	}

	private URL createUrl(String urlSpec, ChannelMessage message)
			throws MalformedURLException {
		UrlBuilder builder = new UrlBuilder(urlSpec);
		builder.appendPath(message.getFunction());
		for (Iterator i = message.getParametersIterator(); i.hasNext();) {
			String parameter = (String) i.next();
			String value = message.valueForParameter(parameter);
			builder.addParameter(parameter, value);
		}
		URL url = builder.toUrl();
		String value = builder.toString();
		LogUtility.logDebug(this, value);
		return url;
	}

	public InputStream send(ChannelMessage message) throws IOException {
		URL url = createUrl(urlSpec, message);
		LogUtility.logDebug(this, "Sending message: " + message.getFunction());
		return url.openStream();
	}
}
