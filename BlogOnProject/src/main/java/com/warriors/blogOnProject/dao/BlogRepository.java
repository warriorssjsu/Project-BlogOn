package com.warriors.blogOnProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.warriors.blogOnProject.entities.Blog;

@Repository
public interface BlogRepository  extends JpaRepository<Blog, Long> {
	
	//@Query("SELECT b FROM Blog b WHERE t.category = ?1")
	
	//Blog findByCategory(String category);
	List<Blog> findAllByUserId(String id);
	
	@Query("select b from Blog b order by likes desc ")
	List<Blog> findTop3OrderBylikesDesc();

	Blog findByTitle(String title);

	
}
