package com.warriors.blogOnProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warriors.blogOnProject.entities.Blog;

@Repository
public interface BlogDao  extends JpaRepository<Blog, Long> {

}
