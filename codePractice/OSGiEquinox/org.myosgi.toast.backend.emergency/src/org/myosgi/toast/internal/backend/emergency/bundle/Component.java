package org.myosgi.toast.internal.backend.emergency.bundle;

import org.myosgi.toast.core.ICoreConstants;
import org.myosgi.toast.core.LogUtility;
import org.myosgi.toast.core.PropertyManager;
import org.myosgi.toast.core.UrlBuilder;
import org.myosgi.toast.core.emergency.IEmergencyConstants;
import org.myosgi.toast.internal.backend.emergency.EmergencyServlet;
import org.osgi.service.http.HttpService;

public class Component {

	private HttpService http;
	private String servletAlias;
	
	public void setHttp(HttpService http) {
		this.http = http;
	}
	
	protected void shutdown(){
		http.unregister(servletAlias);
	}
	
	protected void startup(){
		try {
			String servletRoot = PropertyManager.getProperty(ICoreConstants.BACK_END_URL_PROPERTY, 
					ICoreConstants.BACK_END_URL_DEFAULT);
			UrlBuilder urlBuilder = new UrlBuilder(servletRoot);
			urlBuilder.appendPath(IEmergencyConstants.EMERGENCY_FUNCTION);
			servletAlias = urlBuilder.getPath();
			EmergencyServlet emergencyServlet = new EmergencyServlet();
			http.registerServlet(servletAlias, emergencyServlet, null, null);
			LogUtility.logDebug(this, "Registered EmergencyServlet at "+servletAlias);		
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}	
	}
	
	
	
}
