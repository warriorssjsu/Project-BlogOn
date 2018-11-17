package com.warriors.blogOnProject;

import java.util.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.warriors.blogOnProject.dao.CategoryRepository;
import com.warriors.blogOnProject.entities.Blog;
import com.warriors.blogOnProject.entities.Category;



@SpringBootApplication
@EnableJpaAuditing
public class BlogOnMainClass  extends SpringBootServletInitializer implements CommandLineRunner{
	
	private CategoryRepository categoryRepository;
	
	public BlogOnMainClass(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BlogOnMainClass.class, args);
	}
	
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlogOnMainClass.class);
	}

   @Override
    public void run(String... args) throws Exception{
	   
	   Stream.of("Art", "Technology", "Travel",
               "Science","Social Life","Festivals", "Culture").forEach(name ->
               categoryRepository.save(new Category(name))
       );

	   Category cat = categoryRepository.findByName("Technology");
       Blog b = Blog.builder().title("Full Stack Reactive 1")
               .description("Reactive with Spring Boot + React 1")
               .timestamp(Instant.parse("2018-11-16T18:00:00.000Z"))
               .status("Published")
               //.category(cat)
               .build();
       cat.setBlogs(Collections.singleton(b));
       categoryRepository.save(cat);

       categoryRepository.findAll().forEach(System.out::println);
   }

   
   @Bean
   public RequestContextListener requestContextListener() {
       return new RequestContextListener();
   }
    
   
	

}
