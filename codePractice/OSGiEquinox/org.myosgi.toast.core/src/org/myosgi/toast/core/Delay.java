package org.myosgi.toast.core;


public class Delay {
	public static void milliseconds(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) { // Ignored
		}
	}

	public static void seconds(int seconds) {
		Delay.milliseconds(1000 * seconds);
	}
}
