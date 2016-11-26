package com.example.e4.rcp.todo.parts;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class ResourceUtil {

	/**
	 * Get a Image in the bundle. The image will be automatically disposed when
	 * the composite is disposed.
	 * 
	 * @param pathInBundle
	 *            path of the image in this bundle.
	 * @param composite
	 *            the bound composite
	 * @return the image
	 */
	public static Image getImage(String pathInBundle, Composite composite) {
		Bundle bundle = FrameworkUtil.getBundle(ResourceUtil.class);
		URL url = FileLocator.find(bundle, new Path(pathInBundle), null);
		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
		LocalResourceManager resManager = new LocalResourceManager(
				JFaceResources.getResources(), composite);
		return resManager.createImage(imageDescriptor);
	}

}
