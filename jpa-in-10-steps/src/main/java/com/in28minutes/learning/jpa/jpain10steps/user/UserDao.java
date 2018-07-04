package com.in28minutes.learning.jpa.jpain10steps.user;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

interface UserDao {
	public long insert(User user);
}
