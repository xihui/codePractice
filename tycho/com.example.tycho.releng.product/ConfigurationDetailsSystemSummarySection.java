/**
 * $Id: ConfigurationDetailsSystemSummarySection.java,v 1.2 2012/10/26 03:08:30 ken Exp $
 * 
 * (c) Copyright, Real-Time Innovations, $Date: 2012/10/26 03:08:30 $.
 * All rights reserved.
 * 
 * No duplications, whole or partial, manual or electronic, may be made
 * without express written permission. Any such copies, or
 * revisions thereof, must display this notice unaltered.
 * This code contains trade secrets of Real-Time Innovations, Inc.
 * 
 * modification history:
 * ---------------------
 * 
 * ===========================================================================
 */
 
package com.rti.tools.console;

import java.io.PrintWriter;

import org.eclipse.ui.about.ISystemSummarySection;

public class ConfigurationDetailsSystemSummarySection implements ISystemSummarySection {

	@Override
	public void write(PrintWriter writer) {
		writer.println("Administration Console Configuration" );
        
	}

}
