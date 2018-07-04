package com.in28minutes.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner{
	
	private static final Logger logger=LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new User("Maneesh", "admin"));
		repository.save(new User("Priyanka","admin"));
		repository.save(new User("Ravi","user"));
		repository.save(new User("Vikas","user"));
		
		for (User user: repository.findAll()) {
			logger.info(user.toString());
		}	
		
		logger.info("Admin users are:");
		logger.info("----------------");
		for(User user: repository.findByRole("admin")) {
			logger.info(user.toString());
		}
	}

}
