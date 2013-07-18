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
package org.myosgi.toast.internal.core;

import org.myosgi.toast.core.spi.IStatusIdFinder;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class StatusIdFinder implements IStatusIdFinder {

	public String getStatusId(Object object) {
		Bundle bundle = FrameworkUtil.getBundle(object.getClass());
		if (bundle == null)
			return "org.equinoxosgi.toast.core.default";
		return bundle.getSymbolicName();
	}
}
