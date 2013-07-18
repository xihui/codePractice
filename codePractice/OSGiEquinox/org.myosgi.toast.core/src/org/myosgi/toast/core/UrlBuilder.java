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
package org.myosgi.toast.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UrlBuilder {
	private static class UrlParameter extends Object {
		private final String name;
		private final String value;

		UrlParameter(String name, String value) {
			super();
			if (name == null)
				throw new IllegalArgumentException("name must not be null");
			if (value == null)
				throw new IllegalArgumentException("value must not be null");
			this.name = name;
			this.value = value;
		}

		void printOn(StringBuffer buffer) {
			buffer.append(this.name);
			buffer.append('=');
			buffer.append(this.value);
		}

		public String toString() {
			StringBuffer buffer = new StringBuffer(50);
			printOn(buffer);
			String value = buffer.toString();
			return value;
		}
	}

	private final List parameters;
	private String spec;

	public UrlBuilder(String spec) {
		super();
		if (spec == null)
			throw new IllegalArgumentException("URL spec must not be null");
		this.spec = spec;
		parameters = new ArrayList(5);
	}

	public void addParameter(String name, boolean value) {
		String valueString = String.valueOf(value);
		addParameter(name, valueString);
	}

	public void addParameter(String name, int value) {
		String valueString = String.valueOf(value);
		addParameter(name, valueString);
	}

	public void addParameter(String name, String value) {
		UrlParameter parameter = new UrlParameter(name, value);
		addParameter(parameter);
	}

	private void addParameter(UrlParameter parameter) {
		synchronized (parameters) {
			parameters.add(parameter);
		}
	}

	public void appendPath(String path) {
		// Append to the spec, taking care of the slashes properly
		if (!spec.endsWith("/"))
			spec += '/';
		spec += path;
	}

	public String getPath() throws MalformedURLException {
		// remove protocol, host & port, return a path with leading / and
		// without trailing /
		String answer = toUrl().getFile();
		if (!answer.startsWith("/"))
			answer = "/" + answer;
		if (answer.endsWith("/"))
			answer = answer.substring(0, answer.length() - 1);
		if (answer.length() == 0)
			return "/";
		return answer;
	}

	private void printOn(StringBuffer buffer) {
		buffer.append(spec);
		printParametersOn(buffer);
	}

	private void printParametersOn(StringBuffer buffer) {
		synchronized (parameters) {
			boolean empty = parameters.isEmpty();
			if (empty == true)
				return;
			buffer.append('?');
			Iterator iterator = parameters.iterator();
			while (iterator.hasNext() == true) {
				UrlParameter parameter = (UrlParameter) iterator.next();
				parameter.printOn(buffer);
				boolean last = iterator.hasNext() == false;
				if (last == false) {
					buffer.append('&');
				}
			}
		}
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(150);
		printOn(buffer);
		String value = buffer.toString();
		return value;
	}

	public URL toUrl() throws MalformedURLException {
		String value = toString();
		URL url = new URL(value);
		return url;
	}
}
