package com.in28minutes.learning.jpa.jpain10steps.user;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

@Component
class UserRepositoryCommandLineRunner implements CommandLineRunner{
	
	private static final Logger log=LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	@Autowired
	private UserRepository repository;
	@Override
	public void run(String... args) throws Exception {
		User user=new User("Jill", "Admin");
		repository.save(user);
		log.info("New user is created: "+user);
		
		Optional<User> userWithIdOne= repository.findById(1l);
		log.info("User is retrieved: "+userWithIdOne);
		
		List<User> users = repository.findAll();
		log.info("All Users are: "+users);
	}

}
