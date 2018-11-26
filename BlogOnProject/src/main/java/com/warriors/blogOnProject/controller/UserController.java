package com.warriors.blogOnProject.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.warriors.blogOnProject.AdminService;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private ClientRegistration registration;
    final AdminService adminService;

    public UserController(ClientRegistrationRepository registrations, AdminService adminService) {
        this.registration = registrations.findByRegistrationId("okta");
        this.adminService = adminService;
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
        	System.out.println("user is null");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
        	System.out.println("in getUser method"+user.getName());
        	System.out.println("in getUser role"+user.getAuthorities());
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	System.out.println(authentication);
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
    
    @RequestMapping("/api/admin")  
    public ResponseEntity<?> getAdmin(@AuthenticationPrincipal OAuth2User user) {
    	System.out.println("server side /api/admin");
    	Boolean ensured =adminService.ensureAdmin();  
        if (user == null) {
        	System.out.println("user is null");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
        	System.out.println("in admin method"+user.getName());
        	System.out.println("in admin role"+user.getAuthorities());
            return ResponseEntity.ok().body(user.getAttributes());
        }
    }
	
}