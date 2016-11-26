package org.equinoxosgi.toast;

import EmergencyMonitor;

import org.myosgi.toast.dev.airbag.Airbag;
import org.myosgi.toast.dev.gps.Gps;

public class Main {
	public static void main(String[] args) {
		System.out.println("Launching");
		Gps gps = new Gps();
		Airbag airbag = new Airbag();
		EmergencyMonitor monitor = new EmergencyMonitor();
		monitor.setGps(gps);
		monitor.setAirbag(airbag);
		monitor.startup();
		airbag.deploy();
		monitor.shutdown();
		System.out.println("Terminating");
	}
}
