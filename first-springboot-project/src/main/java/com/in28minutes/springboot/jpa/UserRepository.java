package com.in28minutes.springboot.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="users",collectionResourceRel="users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	Iterable<User> findByRole(@Param("role") String role);
}
