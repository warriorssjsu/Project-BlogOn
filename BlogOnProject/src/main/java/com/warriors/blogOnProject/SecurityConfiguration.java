package com.warriors.blogOnProject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;



//@EnableWebSecurity
@EnableOAuth2Sso
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //@Value("${security.saml2.metadata-url}")
    //String metadataUrl;

    @Value("${server.ssl.key-alias}")
    String keyAlias;

    @Value("${server.ssl.key-store-password}")
    String password;

    @Value("${server.port}")
    String port;

    @Value("${server.ssl.key-store}")
    String keyStoreFilePath;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	System.out.println("In securityConfiguration");
    	
    	
        http
            .authorizeRequests()
                .antMatchers("/saml*","/","/home").permitAll()
                
                //.antMatchers("/**/*.{js,html,css}").permitAll()
             // The rest of the our application is protected.
                .anyRequest().authenticated()
              
                // Configures form login
               // .and().formLogin().loginPage("/login")
               // .failureUrl("/login?error=bad_credentials")
                // Configures the logout function
               // .and().rememberMe().tokenValiditySeconds(60).and().exceptionHandling()
                ;
    }
               /* .and()
            .apply(saml())
                .serviceProvider()
                    .keyStore()
                        .storeFilePath(this.keyStoreFilePath)
                        .password(this.password)
                        .keyname(this.keyAlias)
                        .keyPassword(this.password)
                        .and()
                    .protocol("https")
                    .hostname(String.format("%s:%s", "192.168.99.100", this.port))
                    .basePath("/")
                   // .and()
               // .identityProvider()
                //.metadataFilePath(this.metadataUrl)*/
                   // ;
        
        /*http
        .addFilterBefore(metadataGeneratorFilter(), ChannelProcessingFilter.class);
    }
    
    
    @Bean
    public ExtendedMetadata extendedMetadata() {
    	ExtendedMetadata extendedMetadata = new ExtendedMetadata();
    	extendedMetadata.setIdpDiscoveryEnabled(true); 
    	extendedMetadata.setSignMetadata(false);
    	extendedMetadata.setEcpEnabled(true);
    	return extendedMetadata;
    }
 
    @Bean
    public MetadataGenerator metadataGenerator() {
        MetadataGenerator metadataGenerator = new MetadataGenerator();
        metadataGenerator.setEntityId("https://localhost:8080/saml/metadata");
        metadataGenerator.setExtendedMetadata(extendedMetadata());
        metadataGenerator.setIncludeDiscoveryExtension(false);
        return metadataGenerator;
    }
    /*
    @Bean
    public MetadataGeneratorFilter metadataGeneratorFilter() {
        return new MetadataGeneratorFilter(metadataGenerator());
    }
    */
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
   */ 
      /*  @Bean
        public RequestCache refererRequestCache() {
            return new HttpSessionRequestCache() {
                @Override
                public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
                    String referrer = request.getHeader("referer");
                    if (referrer != null) {
                        request.getSession()
                        .setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer))
                        ;
                    }
                }
            };
        }*/
    }
