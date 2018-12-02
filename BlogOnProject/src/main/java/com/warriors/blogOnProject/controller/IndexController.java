package com.warriors.blogOnProject.controller;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



//@EnableOAuth2Client
@Controller
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