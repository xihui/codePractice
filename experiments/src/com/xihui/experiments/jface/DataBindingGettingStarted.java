package com.xihui.experiments.jface;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DataBindingGettingStarted {
	static class Model {
		private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
		public void addPropertyChangeListener(String propertyName,
				PropertyChangeListener listener) {
			changeSupport.addPropertyChangeListener(propertyName, listener);
		}
		public void removePropertyChangeListener(String propertyName,
				PropertyChangeListener listener) {
			changeSupport.removePropertyChangeListener(propertyName, listener);
		}
		private int amount = 0;
		public void setAmount(int newAmount) {
			int oldAmount = this.amount;
			this.amount = newAmount;
			changeSupport.firePropertyChange("amount", oldAmount, newAmount);
		}
		public int getAmount() {
			return amount;
		}
	}

	static Model model = new Model();
	
	static void init(Shell shell) {
		Text text = new Text(shell, SWT.BORDER);
		Label label = new Label(shell, SWT.NONE);

		Button button = new Button(shell, SWT.PUSH);
		button.setText("Double!");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				model.setAmount(model.getAmount() * 2);
			}
		});
		
		DataBindingContext dbc = new DataBindingContext();

		IObservableValue modelObservable = BeansObservables.observeValue(model, "amount");

		dbc.bindValue(SWTObservables.observeText(text, SWT.Modify), modelObservable, null, null);
		dbc.bindValue(SWTObservables.observeText(label), modelObservable, null, null);
		
		Label errorLabel = new Label(shell, SWT.NONE);
		dbc.bindValue(SWTObservables.observeText(errorLabel),
				new AggregateValidationStatus(dbc.getBindings(),
						AggregateValidationStatus.MAX_SEVERITY), null, null);
		
		// updated line follows:
				Binding b = dbc.bindValue(SWTObservables.observeText(text, SWT.Modify), modelObservable, null, null);

				Label individualErrorLabel = new Label(shell, SWT.NONE);
				dbc.bindValue(SWTObservables.observeText(individualErrorLabel), b.getValidationStatus(), null, null);
		
		GridLayoutFactory.swtDefaults().generateLayout(shell);
	}

	
	public static void main(String[] args) {
		final Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				Shell shell = new Shell(display);
				init(shell);
				shell.pack();
				shell.open();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
			}
		});
		display.dispose();
	}
}
