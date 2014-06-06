package com.xihui.experiments.jface;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ViewTable extends LarsPersonBindingExample {
  private TableViewer viewer;
  private WritableList input;
  

  /**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				Shell shell = new Shell(display);
				shell.pack();
				shell.open();
				ViewTable view = new ViewTable();
				view.createPartControl(shell);
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
			}
		});
		display.dispose();
		

	}

  @Override
  public void createPartControl(Composite parent) {
    parent.setLayout(new GridLayout(1, false));
    GridData gd = new GridData();
    gd.grabExcessHorizontalSpace = true;

    // Define the viewer
    viewer = new TableViewer(parent);
    viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
    column.getColumn().setWidth(100);
    column.getColumn().setText("First Name");
    column = new TableViewerColumn(viewer, SWT.NONE);
    column.getColumn().setWidth(100);
    column.getColumn().setText("Last Name");
    column = new TableViewerColumn(viewer, SWT.NONE);
    column.getColumn().setWidth(100);
    column.getColumn().setText("Married");
    viewer.getTable().setHeaderVisible(true);

    // now lets bind the values
    // No extra label provider / content provider / setInput required
    input = new WritableList(MyModel.getPersons(), Person.class);
    ViewerSupport.bind(viewer,
        input,
        BeanProperties.values(new String[] { "firstName", "lastName",
            "married" }));

    // The following buttons are there to test the binding
    Button delete = new Button(parent, SWT.PUSH);
    delete.setText("Delete");
    delete.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (!viewer.getSelection().isEmpty()) {
          IStructuredSelection selection = (IStructuredSelection) viewer
              .getSelection();
          Person p = (Person) selection.getFirstElement();
          input.remove(p);
        }
      }
    });

    Button add = new Button(parent, SWT.PUSH);
    add.setText("Add");
    add.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        Person p = new Person();
        p.setFirstName("Test1");
        p.setLastName("Test2");
        input.add(p);
      }
    });
    Button change = new Button(parent, SWT.PUSH);
    change.setText("Switch First / Lastname");
    change.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (!viewer.getSelection().isEmpty()) {
          IStructuredSelection selection = (IStructuredSelection) viewer
              .getSelection();
          Person p = (Person) selection.getFirstElement();
          String temp = p.getLastName();
          p.setLastName(p.getFirstName());
          p.setFirstName(temp);
        }
      }
    });
    
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }
} 