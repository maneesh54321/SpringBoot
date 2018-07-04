package com.app.springboot.todo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Todo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String description;
	private TodoStatus status;
	private Date completedAt;

	protected Todo() {
		super();
	}

	public Todo(Long id, String description, TodoStatus status, Date completedAt) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.completedAt = completedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TodoStatus getStatus() {
		return status;
	}

	public void setStatus(TodoStatus status) {
		this.status = status;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", status=" + status + ", completedAt=" + completedAt
				+ "]";
	}
}
