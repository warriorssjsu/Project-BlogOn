package com.warriors.blogOnProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warriors.blogOnProject.entities.BloggerUser;

@Repository
public interface BloggerUserDao extends JpaRepository<BloggerUser, Long>{

}
