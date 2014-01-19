package com.example.e4.rcp.todo.service.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.e4.rcp.todo.model.ITodoService;
import com.example.e4.rcp.todo.model.Todo;

public class MyTodoServiceImpl implements ITodoService {

	private static int current = 1;
	private List<Todo> todos;

	public MyTodoServiceImpl() {
		todos = createInitialModel();
	}

	private List<Todo> createInitialModel() {
		List<Todo> list = new ArrayList<Todo>();
		list.add(createTodo("Hello 1 ", "my work 1"));
		list.add(createTodo("Hello 2", "my work 2"));
		list.add(createTodo("Hello 3 ", "my work 3"));
		list.add(createTodo("Hello 4", "my work 4"));
		list.add(createTodo("Hello 5 ", "my work 5"));
		return list;
	}

	private Todo createTodo(String summary, String description) {
		return new Todo(current++, summary, description, false, new Date());
	}

	@Override
	public Todo getTodo(long id) {
		Todo todo = findById(id);
		if (todo != null)
			return todo.copy();
		return null;
	}

	@Override
	public synchronized boolean saveTodo(Todo newTodo) {
		Todo updateTodo = findById(newTodo.getId());

		if (updateTodo == null) {
			updateTodo = new Todo(current++);
			todos.add(updateTodo);
		}
		updateTodo.setSummary(newTodo.getSummary());
		updateTodo.setDescription(newTodo.getDescription());
		updateTodo.setDone(newTodo.isDone());
		updateTodo.setDueDate(newTodo.getDueDate());

		return true;
	}

	@Override
	public boolean deleteTodo(long id) {
		Todo deleteTodo = findById(id);
		if (deleteTodo != null) {
			todos.remove(deleteTodo);
			return true;
		}
		return false;
	}

	@Override
	public List<Todo> getTodos() {
		List<Todo> list = new ArrayList<Todo>();
		for (Todo todo : todos) {
			list.add(todo.copy());
		}
		return list;
	}

	private Todo findById(long id) {
		for (Todo todo : todos) {
			if (id == todo.getId()) {
				return todo;
			}
		}
		return null;
	}

}
