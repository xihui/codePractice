package org.myosgi.toast.dev.airbag;

public interface IAirbag {

	public abstract void addListener(IAirbagListener listener);

	public abstract void removeListener(IAirbagListener listener);

}