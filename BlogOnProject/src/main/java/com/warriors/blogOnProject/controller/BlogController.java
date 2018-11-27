package com.warriors.blogOnProject.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warriors.blogOnProject.dao.BlogRepository;
import com.warriors.blogOnProject.dao.UserRepository;
import com.warriors.blogOnProject.entities.Blog;
import com.warriors.blogOnProject.entities.User;

@RestController
@RequestMapping("/api")
public class BlogController {
	
	private final Logger log = LoggerFactory.getLogger(BlogController.class);
    private BlogRepository blogRepository;
    private UserRepository userRepository;

    public BlogController(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/blogs")
    Collection<Blog> blogs() {
        return blogRepository.findAll();
    }
    /*Collection<Group> groups(Principal principal) {
        return groupRepository.findAllByUserId(principal.getName());
    }*/
    @GetMapping("/blog/{id}")
    ResponseEntity<?> getBlog(@PathVariable Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/blog")
    ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog, 
    		@AuthenticationPrincipal OAuth2User principal) throws URISyntaxException {
        log.info("Request to create blog: {}", blog);
                
        Map<String, Object> details = principal.getAttributes();
        
        String userId = details.get("sub").toString();
        System.out.println("details userId "+userId);
     // check to see if user already exists
        
       Optional<User> user = userRepository.findById(userId);
       
       blog.setUser(user.orElse(new User(userId,
                    details.get("name").toString(), details.get("email").toString()
                    )));
        
        Blog result = blogRepository.save(blog);
        return ResponseEntity.created(new URI("/api/blog/" + result.getId()))
                .body(result);
    }
    
   
    
    
    
    
    @PutMapping("/blog")
    ResponseEntity<Blog> updateBlog(@Valid @RequestBody Blog blog) {
        log.info("Request to update blog: {}", blog);
        Blog result = blogRepository.save(blog);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("/blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        log.info("Request to delete blog: {}", id);
        blogRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
