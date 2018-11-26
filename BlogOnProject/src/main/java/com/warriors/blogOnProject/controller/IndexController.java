package com.warriors.blogOnProject.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.warriors.blogOnProject.AdminService;
import com.warriors.blogOnProject.dao.CategoryRepository;
import com.warriors.blogOnProject.entities.Category;

//@EnableOAuth2Client
@Controller
//@RequestMapping("/api")
//@RestController

@Profile("prod")
public class IndexController {
		
	@GetMapping("/home")
    public String home(ModelAndView mv) {  
        return "home"; 
    }
	
	//@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/private")
	public String redirectToRoot() {
        return "redirect:/";
    }
	
	/*public void redirectToRoot(HttpServletResponse response) throws IOException {
		response.sendRedirect("/");
    }*/
	
	
}