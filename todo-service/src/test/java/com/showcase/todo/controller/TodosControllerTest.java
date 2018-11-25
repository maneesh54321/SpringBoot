package com.showcase.todo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.showcase.todo.domain.Todo;
import com.showcase.todo.service.TodoService;


@RunWith(MockitoJUnitRunner.class)
public class TodosControllerTest {
	
	private MockMvc mvc;
	
	@Mock
	private TodoService todoService;
	
	@InjectMocks
	private TodosController todoController;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(todoController)
				.build();
	}

	@Test
	public void testGetAllTodos() throws Exception {
		when(todoService.getAllTodos()).thenReturn(CompletableFuture.completedFuture(Arrays.asList(new Todo())));
		
		MockHttpServletResponse reponse = mvc.perform(get("/todos/getAll")).andReturn().getResponse();
		
		assertThat(reponse.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

}
