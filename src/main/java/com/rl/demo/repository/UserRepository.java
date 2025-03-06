package com.rl.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rl.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	    User findByEmail(String email);
	}



