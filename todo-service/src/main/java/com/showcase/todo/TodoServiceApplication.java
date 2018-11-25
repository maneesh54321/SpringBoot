package com.showcase.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TodoServiceApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TodoServiceApplication.class, args);
		setString(new Integer(5));
	}
	
	public static void setString(Integer abc) {
		System.out.println("integer");
	}
	
	public static void setString(int abc) {
		System.out.println("object");
	}
}
