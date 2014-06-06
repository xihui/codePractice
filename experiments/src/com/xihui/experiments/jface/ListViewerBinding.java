package com.xihui.experiments.jface;

import java.util.List;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

// direct usage of ObservableListContentProvider
// listens to the labels changes too via ObservableMapLabelProvider

public class ListViewerBinding extends ViewPart {
  private ListViewer viewer;
  private WritableList input;

	public static void main(String[] args) {
		
		final Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				Shell shell = new Shell(display);
				shell.pack();
				shell.open();
				ListViewerBinding view = new ListViewerBinding();
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

    // define the viewer
    viewer = new ListViewer(parent);
    viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    ObservableListContentProvider contentProvider = new ObservableListContentProvider();
    viewer.setContentProvider(contentProvider);

    // create the label provider including monitoring 
    // of label changes
    IObservableSet knownElements = contentProvider.getKnownElements();
    final IObservableMap firstNames = BeanProperties.value(Person.class,
        "firstName").observeDetail(knownElements);
    final IObservableMap lastNames = BeanProperties.value(Person.class,
        "lastName").observeDetail(knownElements);

    IObservableMap[] labelMaps = { firstNames, lastNames };

    ILabelProvider labelProvider = new ObservableMapLabelProvider(labelMaps) {
      public String getText(Object element) {
        return firstNames.get(element) + " " + lastNames.get(element);
      }
    };

    viewer.setLabelProvider(labelProvider);

    // create sample data
    List<Person> persons = MyModel.getPersons();
    input = new WritableList(persons, Person.class);
    // set the writeableList as input for the viewer
    viewer.setInput(input);

    Button delete = new Button(parent, SWT.PUSH);
    delete.setText("Delete");
    delete.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        deletePerson();
      }

    });

    Button add = new Button(parent, SWT.PUSH);
    add.setText("Add");
    add.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        addPerson();
      }

    });
    Button change = new Button(parent, SWT.PUSH);
    change.setText("Switch First / Lastname");
    change.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        switchFirstLastName();
      }

    });
  }

  public void switchFirstLastName() {
    if (!viewer.getSelection().isEmpty()) {
      IStructuredSelection selection = (IStructuredSelection) viewer
          .getSelection();
      Person p = (Person) selection.getFirstElement();
      String temp = p.getLastName();
      p.setLastName(p.getFirstName());
      p.setFirstName(temp);
    }
  }

  public void deletePerson() {
    if (!viewer.getSelection().isEmpty()) {
      IStructuredSelection selection = (IStructuredSelection) viewer
          .getSelection();
      Person p = (Person) selection.getFirstElement();
      input.remove(p);
    }
  }

  public void addPerson() {
    Person p = new Person();
    p.setFirstName("Test1");
    p.setLastName("Test2");
    input.add(p);
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

} 