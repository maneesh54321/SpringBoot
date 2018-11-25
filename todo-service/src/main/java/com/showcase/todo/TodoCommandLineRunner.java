package com.showcase.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.showcase.todo.domain.Todo;
import com.showcase.todo.repository.TodoRepository;

@Component
public class TodoCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private TodoRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Todo("Learn microservices",false));
		repository.save(new Todo("Learn Angular 6",false));
		repository.save(new Todo("Learn Spring cloud",false));
		repository.save(new Todo("Learn to use oauth2",false));
	}

}
