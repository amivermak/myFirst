package com.frugalis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalis.entity.User;


public interface UserRepository extends JpaRepository<User, Long>,UserRepo {
	

}
