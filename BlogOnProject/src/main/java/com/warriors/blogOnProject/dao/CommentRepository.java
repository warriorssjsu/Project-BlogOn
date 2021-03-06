package com.warriors.blogOnProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warriors.blogOnProject.entities.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

}
