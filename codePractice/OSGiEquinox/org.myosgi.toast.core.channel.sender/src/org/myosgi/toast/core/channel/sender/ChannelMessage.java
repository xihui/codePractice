package org.myosgi.toast.core.channel.sender;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChannelMessage {
	private String function;
	private Map parameters;

	public ChannelMessage(String function) {
		super();
		this.function = function;
		parameters = new HashMap(11);
	}

	public void addParameter(String parameter, int value) {
		addParameter(parameter, Integer.toString(value));
	}

	public void addParameter(String parameter, String value) {
		parameters.put(parameter, value);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelMessage other = (ChannelMessage) obj;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}

	public String getFunction() {
		return function;
	}

	public Iterator getParametersIterator() {
		return parameters.keySet().iterator();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((function == null) ? 0 : function.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}

	public String valueForParameter(String parameter) {
		return (String) parameters.get(parameter);
	}
}
