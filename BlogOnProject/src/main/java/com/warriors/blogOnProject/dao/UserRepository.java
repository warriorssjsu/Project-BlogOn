package com.warriors.blogOnProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.warriors.blogOnProject.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findAllById(String userId);
	
}