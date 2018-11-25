/**
 * 
 */
package com.showcase.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.showcase.todo.domain.Todo;

@CrossOrigin(origins="*")
public interface TodoRepository extends CrudRepository<Todo, Integer> {
	
}
