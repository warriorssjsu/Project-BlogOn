package com.warriors.blogOnProject.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.warriors.blogOnProject.dao.ProfileRepository;
import com.warriors.blogOnProject.dao.UserRepository;
import com.warriors.blogOnProject.entities.Profile;
import com.warriors.blogOnProject.entities.User;

@RestController
@RequestMapping("/api")
public class ProfileController {
	
	private final Logger log = LoggerFactory.getLogger(BlogController.class);
    private ProfileRepository profilegRepository;
    private UserRepository userRepository;
    
    public ProfileController(ProfileRepository profilegRepository, UserRepository userRepository) {
        this.profilegRepository = profilegRepository;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/profile")
    ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile profile, 
    		@AuthenticationPrincipal OAuth2User principal) throws URISyntaxException {
    	log.info("Request to create profile: {}", profile);

    	Map<String, Object> details = principal.getAttributes();
    	String userId = details.get("sub").toString();
    	System.out.println("details userId "+userId);
    	// check to see if user already exists
    	Optional<User> user = userRepository.findById(userId);
    	
    	Profile result = profilegRepository.save(profile);
    	return ResponseEntity.created(new URI("/api/profile/" + result.getId()))
    			.body(result);
    }
    
    
    @GetMapping("/profile/{profileuser}")
    ResponseEntity<?> getProfile(@PathVariable String profileuser) {
    	System.out.println("Reaching till here");
        Optional<Profile> profile = profilegRepository.findById(profileuser);
        Profile result;
        if(!profile.isPresent()) {
        	result =	profilegRepository.save(new Profile(profileuser,
                    "","","",""));}
        else {
        	result = profile.get();
        }
        
        return ResponseEntity.ok().body(result);
    }

}
