package com.example.tycho.plugin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class ExampleView extends ViewPart {
	public static final String VIEW_ID = "com.example.tycho.views.example";

	private Label lblNewLabel;

	public ExampleView() {
	}

	@Override
	public void createPartControl(final Composite parent) {

		lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText("Hello world!");
	}

	@Override
	public void setFocus() {
		lblNewLabel.setFocus();
	}
}
