package com.warriors.blogOnProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.warriors.blogOnProject.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
}