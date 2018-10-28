package com.warriors.blogOnProject.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class IndexController {
	
	/*@RequestMapping("/")
    public ModelAndView index(Principal principal,ModelAndView mv) {  
		System.out.println(principal.getName());
		mv.addObject("name", principal.getName());
        return new ModelAndView("home"); 
    }
	
	/*@RequestMapping("/login")
    public ModelAndView login(Principal principal,ModelAndView mv) {  
		System.out.println(principal.getName().toString());
		mv.addObject("name", principal.getName());
        return new ModelAndView("homeafterlogin"); 
    }*/
}