package org.myosgi.toast.internal.client.emergency;
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


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.myosgi.toast.core.ICoreConstants;
import org.myosgi.toast.core.LogUtility;
import org.myosgi.toast.core.PropertyManager;
import org.myosgi.toast.core.channel.sender.ChannelMessage;
import org.myosgi.toast.core.channel.sender.IChannel;
import org.myosgi.toast.core.emergency.IEmergencyConstants;
import org.myosgi.toast.dev.airbag.IAirbag;
import org.myosgi.toast.dev.airbag.IAirbagListener;
import org.myosgi.toast.dev.gps.IGps;

public class EmergencyMonitor implements IAirbagListener {
	private IAirbag airbag;
	private IGps gps;
	private IChannel channel;
	private Job job;

	public void deployed() {
		startJob();
	}
	
	private void runEmergencyProcess(){
		ChannelMessage message = new ChannelMessage(IEmergencyConstants.EMERGENCY_FUNCTION);
		String id = PropertyManager.getProperty(ICoreConstants.ID_PROPERTY, 
				ICoreConstants.ID_DEFAULT);
		message.addParameter(ICoreConstants.ID_PARAMETER, id);
		message.addParameter(IEmergencyConstants.LATITUDE_PARAMETER, gps.getLatitude());
		message.addParameter(IEmergencyConstants.LONGITUDE_PARAMETER, gps.getLongitude());
		message.addParameter(IEmergencyConstants.HEADING_PARAMETER, gps.getHeading());
		message.addParameter(IEmergencyConstants.SPEED_PARAMETER, gps.getSpeed());
		InputStream stream=null;
		try{
			stream = channel.send(message);
			InputStreamReader reader =new InputStreamReader(stream);
			try{
				BufferedReader buffer = new BufferedReader(reader);
				String reply = buffer.readLine();
				LogUtility.logDebug(this, "Received reply: " + reply);				
			}finally{
				stream.close();
			}
		}catch (Exception e) {
			LogUtility.logError(this, "Unable to send to back end: ", e);
		}
		job=null;
	}

	public void setAirbag(IAirbag value) {
		airbag = value;
	}

	public void setGps(IGps value) {
		gps = value;
	}
	
	public void setChannel(IChannel channel) {
		this.channel = channel;
	}

	public void shutdown() {
		stopJob();
		airbag.removeListener(this);
	}
	
	private void startJob(){
		if(job != null)
			return;
		job = new Job("EmergenecyMonitor") {
			
			protected IStatus run(IProgressMonitor monitor) {
				runEmergencyProcess();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	public void startup() {
		airbag.addListener(this);
	}
	private void stopJob(){
		if(job != null){
			job.cancel();
			try {
				job.join();
			} catch (InterruptedException e) {				
			}
			job=null;
		}
	}
}
