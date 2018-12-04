package com.warriors.blogOnProject.dao;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.warriors.blogOnProject.entities.Blog;
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogRepositoryTest {
	 
	    @Autowired
	    private BlogRepository blogRepository;
	 
	    @Test
	    public void whenFindByTitle_thenReturnBlog() {
	        // given
	        Blog blog1 = new Blog("how you doing");
	        blogRepository.saveAndFlush(blog1);    
	        // when
	        Blog found = blogRepository.findByTitle(blog1.getTitle());
	     
	        assertEquals(found.getTitle(), blog1.getTitle());
	        blogRepository.deleteById(found.getId());
	    }
	    
	    @Test
	    public void whenFindAll_thenReturnBlogs() {
	          
	        List<Blog> blogs = blogRepository.findAll();
	     
	        assertNotNull(blogs);
	    }
	    
	    @Test
	    public void whenFindTop_thenReturnTopBlogs() {
	          
	        List<Blog> blogs = blogRepository.findTop3OrderBylikesDesc();
	     
	        assertNotNull(blogs);
	    }
	 
	 
	}

