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
		for (int i = 0; i < 1000; i++)
			list.add(createTodo("Hello " + i, ""));
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
		for (Todo todo : todos) {
			todo.setDescription("Hello" + Math.random());
		}
		return todos;
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
