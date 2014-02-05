package com.example.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.example.e4.bundleresourceloader.IBundleResourceLoader;

public class PlaygroundPart {

	public PlaygroundPart() {
		System.out.println("started");
	}
	
	@PostConstruct
	public void createControls(Composite parent, IBundleResourceLoader loader){
		Label label = new Label(parent, SWT.NONE);
		label.setImage(loader.loadImage(this.getClass(), "images/photo.jpg"));
	}
}
