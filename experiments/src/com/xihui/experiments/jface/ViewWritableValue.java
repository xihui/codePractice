package com.xihui.experiments.jface;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ViewWritableValue extends LarsPersonBindingExample {
	
	public static void main(String[] args) {
		
		final Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				Shell shell = new Shell(display);
				shell.pack();
				shell.open();
				ViewWritableValue view = new ViewWritableValue();
				view.createPartControl(shell);
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
			}
		});
		display.dispose();
		

	}

	
  private WritableValue value;

  @Override
  public void createPartControl(Composite parent) {
    value = new WritableValue();
    value.setValue(new Person());
    parent.setLayout(new GridLayout(3, false));
    GridData gd = new GridData();
    gd.grabExcessHorizontalSpace = true;
    Text text = new Text(parent, SWT.BORDER);
    Button button = new Button(parent, SWT.PUSH);
    button.setText("New Person");
    button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        Person p = new Person();
        p.setFirstName("Lars");
        value.setValue(p);
      }
    });

    button = new Button(parent, SWT.PUSH);
    button.setText("Another Person");
    button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        Person p = new Person();
        p.setFirstName("Jack");
        value.setValue(p);
      }
    });
    
    Button button1 = new Button(parent, SWT.PUSH);
    button1.setText("Write model");
    button1.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent e) {
    	  Person person = (Person) value.getValue();
        System.out.println("Firstname: " + person.getFirstName());
        System.out.println("Age " + person.getAge());
        System.out.println("Married: " + person.isMarried());
        System.out.println("Gender: " + person.getGender());
    
      }
    });
    DataBindingContext ctx = new DataBindingContext();
    IObservableValue target = WidgetProperties.text(SWT.Modify).observe(text);
    IObservableValue model = BeanProperties.value("firstName")
        .observeDetail(value);
    ctx.bindValue(target, model);
  }

  @Override
  public void setFocus() {
  }
} 