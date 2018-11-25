package com.showcase.todo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showcase.todo.domain.Todo;
import com.showcase.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodosController {
	
	@Autowired
	TodoService todoService;
	
	@GetMapping("/getAll")
	public void getAllTodos() {
		CompletableFuture<List<Todo>> future = todoService.getAllTodos();	
		future.handleAsync(new BiFunction<List<Todo>, Throwable, String>() {

			@Override
			public String apply(List<Todo> t, Throwable u) {
				System.out.println(t.size());
				return null;
			}
		});
	}
}
