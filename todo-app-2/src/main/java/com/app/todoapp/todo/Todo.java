package com.app.todoapp.todo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String description;
	private Boolean completed;
	private Date completedAt;

	public Todo() {
		super();
	}

	public Todo(String description, Boolean completed, Date completedAt) {
		super();
		this.description = description;
		this.completed = completed;
		this.completedAt = completedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", completed=" + completed + ", completedAt="
				+ completedAt + "]";
	}
}
