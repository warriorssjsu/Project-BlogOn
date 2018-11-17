package com.warriors.blogOnProject.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warriors.blogOnProject.dao.CategoryRepository;
import com.warriors.blogOnProject.dao.UserRepository;
import com.warriors.blogOnProject.entities.Category;

@RestController
@RequestMapping("/api")
public class CategoryController {

	    private final Logger log = LoggerFactory.getLogger(CategoryController.class);
	    private CategoryRepository categoryRepository;
	    private UserRepository userRepository;

	    public CategoryController(CategoryRepository categoryRepository, UserRepository userRepository) {
	        this.categoryRepository = categoryRepository;
	        this.userRepository = userRepository;
	    }

	    @GetMapping("/categories")
	    @CrossOrigin(origins = "http://localhost:3000")
	    Collection<Category> categories(Principal principal) {
	    	System.out.println("in api/categories method "+(principal.getName()));
	    	System.out.println(categoryRepository.findAll());
	    	return categoryRepository.findAll();
	    }

	 /*   @GetMapping("/category/{id}")
	    ResponseEntity<?> getGroup(@PathVariable Long id) {
	        Optional<Category> cat = categoryRepository.findById(id);
	        return cat.map(response -> ResponseEntity.ok().body(response))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PostMapping("/category")
	    ResponseEntity<Category> createGroup(@Valid @RequestBody Category cat) throws URISyntaxException {
	        log.info("Request to create group: {}", cat);
	        Category result = categoryRepository.save(cat);
	        return ResponseEntity.created(new URI("/api/category/" + result.getId()))
	                .body(result);
	    }

	    @PutMapping("/category/{id}")
	    ResponseEntity<Category> updateGroup(@PathVariable Long id, @Valid @RequestBody Category cat) {
	        cat.setId(id);
	        log.info("Request to update group: {}", cat);
	        Category result = categoryRepository.save(cat);
	        return ResponseEntity.ok().body(result);
	    }

	    @DeleteMapping("/category/{id}")
	    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
	        log.info("Request to delete group: {}", id);
	        categoryRepository.deleteById(id);
	        return ResponseEntity.ok().build();
	    }*/
	}
