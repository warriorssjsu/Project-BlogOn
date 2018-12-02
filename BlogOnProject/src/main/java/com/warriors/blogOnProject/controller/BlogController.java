package com.warriors.blogOnProject.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
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
    
    @GetMapping("/myblogs")
    Collection<Blog> myblogs(@AuthenticationPrincipal OAuth2User principal) throws URISyntaxException {
       Map<String, Object> details = principal.getAttributes();        
        String userId = details.get("sub").toString();
        return blogRepository.findAllByUserId(userId);
    }
    
    @GetMapping("/topblogs")
    Collection<Blog> topblogs() {
    	List<Blog> blogs = blogRepository.findTop3OrderBylikesDesc();
    	
    	System.out.println(blogs.subList(0, 3));
    	
        return blogs.subList(0, 3);
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

    	String role  = "user";

    	Map<String, Object> details = principal.getAttributes();

    	String userId = details.get("sub").toString();
    	System.out.println("details userId "+userId);
    	
    	// check to see if user already exists

    	Optional<User> user = userRepository.findById(userId);
    	String grp =details.get("groups").toString();
    	System.out.println("groups "+grp);
    	if(grp.contains("Admin")) {

    		System.out.println("in if");
    		role= "Admin";
    	} 

    	blog.setUser(user.orElse(new User(userId,
    			details.get("name").toString(), details.get("email").toString(), role
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
    
    @GetMapping("/likeblog/{id}")
    int LikeBlog(@PathVariable Long id) {
        log.info("Request to like blog: {}");
        Optional<Blog> blog = blogRepository.findById(id);
        int likes = blog.get().getLikes() +1;
        System.out.println("likes" + likes);
        blog.get().setLikes(likes);
        System.out.println("updated likes" + blog.get().getLikes());
        blogRepository.save(blog.get());
        return blog.get().getLikes();
    }
    
    @GetMapping("/shareblog/{id}")
    int ShareBlog(@PathVariable Long id) {
        log.info("Request to share blog: {}");
        Optional<Blog> blog = blogRepository.findById(id);
        int shares = blog.get().getShares() +1;
        System.out.println("shares" + shares);
        blog.get().setShares(shares);
        System.out.println("updated shares" + blog.get().getShares());
        blogRepository.save(blog.get());
        return blog.get().getShares();
    }

}
