package org.myosgi.toast.internal.core;

import org.myosgi.toast.core.LogUtility;
import org.osgi.service.log.LogService;

public class Component {
	public void clearLog(LogService log) {
		LogUtility.getInstance().setLog(null);
	}

	public void setLog(LogService log) {
		LogUtility.getInstance().setLog(log);
	}
}
