package com.app.springboot.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class TodoWebSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passEncoder=new BCryptPasswordEncoder(5);
		String encodedPass=passEncoder.encode("admin");
		auth.inMemoryAuthentication()
			.passwordEncoder(passEncoder)
				.withUser("admin")
				.password(encodedPass)
				.roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().and()
			.authorizeRequests()
				.antMatchers("/index.html","/","/templates/**").permitAll()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.anyRequest().authenticated()
				.and()
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}
