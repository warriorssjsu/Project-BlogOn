package com.warriors.blogOnProject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest.Builder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//@EnableOAuth2Sso
@EnableWebSecurity
//@EnableOAuth2Client
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //@Value("${security.saml2.metadata-url}")
    //String metadataUrl;

   /* @Value("${server.ssl.key-alias}")
    String keyAlias;

    @Value("${server.ssl.key-store-password}")
    String password;

    @Value("${server.port}")
    String port;

    @Value("${server.ssl.key-store}")
    String keyStoreFilePath;*/
    
   /* @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("https://localhost:8443/profile");
        //handler.setUseReferer(true);
        return handler;
    }*/

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	System.out.println("In securityConfiguration");
    	
    	 /*  http
            .authorizeRequests()
                .antMatchers("/saml*","/","/home").permitAll()
                
             // The rest of the our application is protected.
                .anyRequest().authenticated()
              
                // Configures form login
               //.and().formLogin().loginPage("/login")
               //.successHandler(successHandler())
               //.failureUrl("/login?error=bad_credentials")
                // Configures the logout function
              // .and().rememberMe().tokenValiditySeconds(60).and().exceptionHandling()
                .and()
                .logout().logoutSuccessUrl("/");
                ;
    }*/
    	http.cors().and()
    	.oauth2Login().and()
    	.csrf()
    	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
    .authorizeRequests()
        .antMatchers("/**/*.{js,html,css}").permitAll()
        .antMatchers("/", "/api/user").permitAll()
        .anyRequest().authenticated();
    	
}

@Bean
@Profile("dev")
public HttpSessionRequestCache refererRequestCache() {
    return new HttpSessionRequestCache() {
        @Override
        public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
            String referrer = request.getHeader("referer");
            System.out.println("referrer"+referrer);
            if (referrer != null) {
                request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer) );
            }
        }
    };
} 


@Bean
GrantedAuthorityDefaults grantedAuthorityDefaults() { 
	System.out.println("in removing ROLE_");
    return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix  
}
   
    
    }
