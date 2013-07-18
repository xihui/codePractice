package org.myosgi.toast.internal.dev.gps.fake;

import org.myosgi.toast.dev.gps.IGps;

public class FakeGps implements IGps {

	/* (non-Javadoc)
	 * @see org.myosgi.toast.dev.gps.IGps#getHeading()
	 */
	public int getHeading() {
		return 90; // 90 degrees (east)
	}

	/* (non-Javadoc)
	 * @see org.myosgi.toast.dev.gps.IGps#getLatitude()
	 */
	public int getLatitude() {
		return 3776999; // 37.76999 N
	}

	/* (non-Javadoc)
	 * @see org.myosgi.toast.dev.gps.IGps#getLongitude()
	 */
	public int getLongitude() {
		return -12244694; // 122.44694 W
	}

	/* (non-Javadoc)
	 * @see org.myosgi.toast.dev.gps.IGps#getSpeed()
	 */
	public int getSpeed() {
		return 50; // 50 kph
	}
	
}
