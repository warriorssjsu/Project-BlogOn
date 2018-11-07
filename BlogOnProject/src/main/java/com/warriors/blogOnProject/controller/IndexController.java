package com.warriors.blogOnProject.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.warriors.blogOnProject.dao.CategoryDao;
import com.warriors.blogOnProject.entities.Category;

@EnableWebMvc
@Controller
public class IndexController {
	
	@Autowired
    CategoryDao categoryDao;
	
	/*@RequestMapping("/")
    public ModelAndView index(Principal principal,ModelAndView mv) {  
		System.out.println(principal.getName());
		mv.addObject("name", principal.getName());
        return new ModelAndView("home"); 
    }*/
	@RequestMapping("/")
    public String index(ModelAndView mv) {  
        return "home"; 
    }
	@RequestMapping("/home")
    public ModelAndView Home(ModelAndView mv) {  
		
        return new ModelAndView("home"); 
    }
	
	@RequestMapping("/login")
    public String login(Principal principal,ModelAndView mv) {  
		 return "homeafterlogin"; 
    }
	
	@PostMapping("/categories")
	public Category createCategories(@Valid @RequestBody Category category) {
		category.setName("Art");
	    return categoryDao.save(category);
	}
}