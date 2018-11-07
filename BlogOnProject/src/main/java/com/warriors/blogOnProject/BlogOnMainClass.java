package com.warriors.blogOnProject;

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

import com.warriors.blogOnProject.dao.CategoryDao;
import com.warriors.blogOnProject.entities.Category;



@SpringBootApplication
@EnableJpaAuditing
public class BlogOnMainClass  extends SpringBootServletInitializer implements CommandLineRunner{
	
	private CategoryDao categoryDao;
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BlogOnMainClass.class, args);
	}
	
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlogOnMainClass.class);
	}

   @Override
    public void run(String... args) throws Exception{
	/*   Category category  = getCategory();
	   categoryDao.createCategory(category);
   }
	   private Category getCategory() {
   	   Category category = new Category();
   	   category.setName("Art");
	   return category;
   	*/
	}
   
   @Bean
   public RequestContextListener requestContextListener() {
       return new RequestContextListener();
   }
    
   
	

}
