package com.in28minutes.learning.jpa.jpain10steps.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

interface UserRepository extends JpaRepository<User, Long>{

}
