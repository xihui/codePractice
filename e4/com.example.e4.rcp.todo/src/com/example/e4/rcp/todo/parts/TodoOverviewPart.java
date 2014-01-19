package com.example.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.widgets.Composite;

public class TodoOverviewPart {

	@Inject
	public TodoOverviewPart(Composite parent) {
		System.out.println("Injected! " + parent.getClass() + " Layout:"
				+ parent.getLayout());

	}

	// This method is called after everything has been injected.
	@PostConstruct
	public void createControls(Composite parent) {
		System.out.println(this.getClass().getSimpleName()
				+ " @PostContstruct method is called. parent = " + parent);
	}

	@Focus
	private void setFocus() {
		System.out.println(this.getClass().getSimpleName()
				+ " @Focus method is called.");

	}



}
