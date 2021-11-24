package com.codingdojo.waterconsumption.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.waterconsumption.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
