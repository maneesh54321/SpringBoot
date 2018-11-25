package com.showcase.todo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.showcase.todo.domain.Todo;
import com.showcase.todo.repository.TodoRepository;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {
	
	@Mock
	TodoRepository todoRepository;
	
	@InjectMocks
	TodoService todoService;

	@Test
	public void testGetAllTodos() {
		when(todoRepository.findAll()).thenReturn(Arrays.asList(new Todo(), new Todo()));
		
		assertEquals(2, ((Collection<Todo>)todoRepository.findAll()).size());
	}

}
