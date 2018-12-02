package com.warriors.blogOnProject.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.warriors.blogOnProject.dao.UserRepository;
import com.warriors.blogOnProject.entities.Blog;
import com.warriors.blogOnProject.entities.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private ClientRegistration registration;
    private UserRepository userRepository;

    public UserController(ClientRegistrationRepository registrations, UserRepository userRepository ) {
        this.registration = registrations.findByRegistrationId("okta");
        this.userRepository =userRepository;
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
    	String role  = "user";
    	System.out.println("authentication type "+this.registration.getAuthorizationGrantType());

    	if (user == null) {
    		System.out.println("user is null");
    		return new ResponseEntity<>("", HttpStatus.OK);
    	} else {

    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		System.out.println(authentication);
    		Map<String, Object> details = user.getAttributes();            
    		String userId = details.get("sub").toString();
    		Optional<User> optUser = userRepository.findById(userId);
    		if(!optUser.isPresent()) {
    			String grp =details.get("groups").toString();
    			System.out.println("groups "+grp);
    			if(grp.contains("Admin")) {

    				System.out.println("in if");
    				role= "Admin";
    			}            
    			userRepository.save(new User(userId,
    					details.get("name").toString(), details.get("email").toString(),role));}
    		return ResponseEntity.ok().body(user.getAttributes());
    	}
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
        // send logout URL to client so they can initiate logout
        String logoutUrl = this.registration.getProviderDetails()
                .getConfigurationMetadata().get("end_session_endpoint").toString();
        System.out.println("logout url "+logoutUrl);

        Map<String, String> logoutDetails = new HashMap<>();
        logoutDetails.put("logoutUrl", logoutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());
        request.getSession(false).invalidate();
        return ResponseEntity.ok().body(logoutDetails);
    }
    
    
    @GetMapping("/api/role")
    public ResponseEntity<?> getRole(@AuthenticationPrincipal OAuth2User principal) {
    	System.out.println("In /api/role/id method ");
        Map<String, Object> details = principal.getAttributes();
        
        String userId = details.get("sub").toString();
    	Optional<User> optUser =userRepository.findById(userId);
    	
    	return optUser.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
}