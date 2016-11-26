package com.example.e4.rcp.todo.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.example.e4.rcp.todo.model.ITodoService;
import com.example.e4.rcp.todo.model.Todo;

public class TodoOverviewPart {

	@Inject
	ITodoService todoService;
	private TableViewer viewer;
	private Text search;
	private String searchString = "";
	@Inject
	public TodoOverviewPart(Composite parent) {
		System.out.println("Injected! " + parent.getClass() + " Layout:"
				+ parent.getLayout());

	}

	// This method is called after everything has been injected.
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Button button = new Button(parent, SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Todo> todos = todoService.getTodos();
				viewer.setInput(todos);
				viewer.setItemCount(todos.size());
			}
		});		

		button.setText("Load Data");
		
		search = new Text(parent, SWT.SEARCH | SWT.CANCEL|SWT.ICON_SEARCH);
		
		search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		search.setMessage("Filter");
		
		search.addModifyListener(new ModifyListener() {
			


			@Override
			public void modifyText(ModifyEvent e) {
				Text source = (Text)e.getSource();
				searchString = source.getText();
				viewer.refresh();
			}
		});

		search.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.detail == SWT.CANCEL) {
					Text text = (Text) e.getSource();
					text.setText("");
				}
			}
		});
		// Virtual Table
		// viewer = new TableViewer(parent, SWT.VIRTUAL | SWT.MULTI
		// | SWT.FULL_SELECTION);
		viewer = new TableViewer(parent, SWT.MULTI | SWT.FULL_SELECTION);
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		// viewer.setContentProvider(new TodoLazyContentProvider(viewer));
		viewer.setContentProvider(new ArrayContentProvider());

		TableViewerColumn colSummary = new TableViewerColumn(viewer, SWT.NONE);
		colSummary.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return ((Todo) element).getSummary();
			}
		});

		colSummary.getColumn().setWidth(100);
		colSummary.getColumn().setText("Summary");

		TableViewerColumn colDescription = new TableViewerColumn(viewer,
				SWT.NONE);
		colDescription.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return ((Todo) element).getDescription();
			}
		});

		colDescription.getColumn().setWidth(200);
		colDescription.getColumn().setText("Description");

		viewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				if (element == null)
					return true;
				Todo todo = (Todo) element;
				return todo.getSummary().contains(searchString)
						|| todo.getDescription().contains(searchString);
			}
		});

		// viewer.setUseHashlookup(true);
		List<Todo> todos = todoService.getTodos();
		viewer.setInput(todos);
		// viewer.setItemCount(todos.size());

	}

	@Focus
	private void setFocus() {
		viewer.getControl().setFocus();
	}

	private class TodoLazyContentProvider implements ILazyContentProvider {

		private TableViewer viewer;

		public TodoLazyContentProvider(TableViewer viewer) {
			this.viewer = viewer;
		}

		private List<Todo> elements;

		@SuppressWarnings("unchecked")
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			this.elements = (List<Todo>) newInput;
		}

		@Override
		public void dispose() {

		}

		@Override
		public void updateElement(int index) {
			viewer.replace(elements.get(index), index);
		}
	}

}
