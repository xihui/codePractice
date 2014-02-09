package com.example.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.example.e4.bundleresourceloader.IBundleResourceLoader;

public class PlaygroundPart {

	public PlaygroundPart() {
		System.out.println("playground started");
	}

	@PostConstruct
	public void createControls(Composite parent, IBundleResourceLoader loader) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label lblPleaseEnterA = new Label(parent, SWT.None);
		lblPleaseEnterA.setText("Please Enter a value:");
		Text text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_text.horizontalIndent = 8;
		text.setLayoutData(gd_text);
		GridData data = new GridData(SWT.FILL, SWT.TOP, true, false);
		text.setData(data);

		final ControlDecoration deco = new ControlDecoration(text, SWT.TOP
				| SWT.LEFT);
		Image image = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION)
				.getImage();
		deco.setDescriptionText("Use CNTL+SPACE to see possible values");
		deco.setImage(image);

		deco.setShowOnlyOnFocus(false);

		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.getSource();
				if (!text.getText().isEmpty()) {
					deco.hide();
				} else {
					deco.show();
				}
			}
		});

		char[] autoActivationChars = new char[] { '.', '#' };
		try {
			KeyStroke keyStroke = KeyStroke.getInstance("Ctrl+Space");
			ContentProposalAdapter adapter = new ContentProposalAdapter(text,
					new TextContentAdapter(),
					new SimpleContentProposalProvider(new String[] {
							"ProposalOne", "two", "three" }), keyStroke,
					autoActivationChars);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Label lblTest = new Label(parent, SWT.None);
		lblTest.setImage(ResourceUtil.getImage("images/photo.jpg", parent));

	}

}
