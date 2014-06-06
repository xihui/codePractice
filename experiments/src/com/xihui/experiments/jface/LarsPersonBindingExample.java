package com.xihui.experiments.jface;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.conversion.ObjectToStringConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class LarsPersonBindingExample extends ViewPart {
  public static final String ID = "de.vogella.databinding.person.swt.View";
  private Person person;

  private Text firstName;
  private Text ageText;
  private Button marriedButton;
  private Combo genderCombo;
  private Text countryText;
  
  private WritableValue personWrapper; 

  
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
				LarsPersonBindingExample view = new LarsPersonBindingExample();
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

    person = createPerson();
    personWrapper= new WritableValue();
    personWrapper.setValue(person);
    // Lets put thing to order
    GridLayout layout = new GridLayout(2, false);
    layout.marginRight = 5;
    parent.setLayout(layout);

    Label firstLabel = new Label(parent, SWT.NONE);
    firstLabel.setText("Firstname: ");
    firstName = new Text(parent, SWT.BORDER);

    GridData gridData = new GridData();
    gridData.horizontalAlignment = SWT.FILL;
    gridData.grabExcessHorizontalSpace = true;
    firstName.setLayoutData(gridData);

    Label ageLabel = new Label(parent, SWT.NONE);
    ageLabel.setText("Age: ");
    ageText = new Text(parent, SWT.BORDER);
//    ageText.addVerifyListener(new VerifyListener() {
//		
//		@Override
//		public void verifyText(VerifyEvent e) {
//			if(!e.text.matches("\\d*"))
//				e.doit=false;
//		}
//	});
   
    gridData = new GridData();
    gridData.horizontalAlignment = SWT.FILL;
    gridData.grabExcessHorizontalSpace = true;
    ageText.setLayoutData(gridData);

    Label marriedLabel = new Label(parent, SWT.NONE);
    marriedLabel.setText("Married: ");
    marriedButton = new Button(parent, SWT.CHECK);

    Label genderLabel = new Label(parent, SWT.NONE);
    genderLabel.setText("Gender: ");
    genderCombo = new Combo(parent, SWT.NONE);
    genderCombo.add("Male");
    genderCombo.add("Female");

    Label countryLabel = new Label(parent, SWT.NONE);
    countryLabel.setText("Country");
    countryText = new Text(parent, SWT.BORDER);

    Button button1 = new Button(parent, SWT.PUSH);
    button1.setText("Write model");
    button1.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        System.out.println("Firstname: " + person.getFirstName());
        System.out.println("Age " + person.getAge());
        System.out.println("Married: " + person.isMarried());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Country: "
            + person.getAddress().getCountry());
      }
    });

    Button button2 = new Button(parent, SWT.PUSH);
    button2.setText("Change model");
    button2.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
    	 person = createPerson();
        person.setFirstName("Lars");
        person.setAge(person.getAge() + 1);
        person.setMarried(!person.isMarried());
        if (person.getGender().equals("Male")) {
          person.setGender("Male");
        } else {
          person.setGender("Female");
        }
        if (person.getAddress().getCountry().equals("Deutschland")) {
          person.getAddress().setCountry("USA");
        } else {
          person.getAddress().setCountry("Deutschland");
        }
        personWrapper.setValue(person);
      }
    });

    // now lets do the binding
    bindValues();
  }

  private Person createPerson() {
    Person person = new Person();
    Address address = new Address();
    address.setCountry("Deutschland");
    person.setAddress(address);
    person.setFirstName("John");
    person.setLastName("Doo");
    person.setGender("Male");
    person.setAge((int) (Math.random()*100000));
    person.setMarried(true);
    return person;
  }

  @Override
  public void setFocus() {
  }

  private void bindValues() {
    // The DataBindingContext object will manage the databindings
    // Lets bind it
    DataBindingContext ctx = new DataBindingContext();
    IObservableValue widgetValue = WidgetProperties.text(SWT.Modify)
        .observe(firstName);
    IObservableValue modelValue = BeanProperties.value(Person.class,
        "firstName").observeDetail(personWrapper);
    ctx.bindValue(widgetValue, modelValue);

    // Bind the age including a validator
    widgetValue = WidgetProperties.text(SWT.Modify).observe(ageText);
    modelValue = BeanProperties.value(Person.class, "age").observeDetail(personWrapper);
    // add an validator so that age can only be a number
    IValidator validator = new IValidator() {
      @Override
      public IStatus validate(Object value) {
        if (value instanceof Integer) {
          String s = String.valueOf(value);
          if (s.matches("\\d*")) {
            return ValidationStatus.ok();
          }
        }
        return ValidationStatus.error("Not a number");
      }
    };

    UpdateValueStrategy strategy = new UpdateValueStrategy();
//    strategy.setBeforeSetValidator(validator);
   UpdateValueStrategy s2 = new UpdateValueStrategy();
   s2.setConverter(new Converter(Integer.class, String.class) {
		
		@Override
		public Object convert(Object fromObject) {
			return fromObject.toString();
		}
	});
    Binding bindValue = ctx.bindValue(widgetValue, modelValue);
    // add some decorations
    ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);

    widgetValue = WidgetProperties.selection().observe(marriedButton);
    modelValue = BeanProperties.value(Person.class, "married").observeDetail(personWrapper);
    ctx.bindValue(widgetValue, modelValue);

    widgetValue = WidgetProperties.selection().observe(genderCombo);
    modelValue = BeanProperties.value("gender").observeDetail(personWrapper);

    ctx.bindValue(widgetValue, modelValue);

    // address field is bound to the Ui
    widgetValue = WidgetProperties.text(SWT.Modify).observe(countryText);

    modelValue = BeanProperties.value(Person.class, "address.country")
    		.observeDetail(personWrapper);
    ctx.bindValue(widgetValue, modelValue);

  }
} 