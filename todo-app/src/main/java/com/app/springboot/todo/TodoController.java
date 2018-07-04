package com.app.springboot.todo;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	@Autowired
	private TodoRepository repository;

	@GetMapping("/todos/")
	public List<Todo> getTodos() {
		return repository.findAll();
	}

	@PostMapping("/todos/add")
	public void addTodo(@RequestBody Todo todo) {
		repository.save(todo);
	}

	@DeleteMapping("/todos/{id}")
	public void deleteTodo(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PutMapping("/todos/")
	public void updateTodo(@RequestBody Todo todo) {
		if (todo.getStatus() == TodoStatus.Completed) {
			todo.setCompletedAt(new Date(Calendar.getInstance().getTimeInMillis()));
		} else {
			todo.setCompletedAt(null);
		}
		repository.save(todo);
	}
}
