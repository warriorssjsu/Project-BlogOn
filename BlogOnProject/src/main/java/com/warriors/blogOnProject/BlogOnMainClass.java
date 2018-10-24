package com.warriors.blogOnProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import com.warriors.blogOnProject.dao.CategoryDao;
import com.warriors.blogOnProject.entities.Category;

@SpringBootApplication
public class BlogOnMainClass  extends SpringBootServletInitializer implements CommandLineRunner{
	
	@Autowired
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
    	Category category = getCategory();
    	categoryDao.createCategory(category);
	}
    
    private Category getCategory() {
    	Category category = new Category();
    	category.setName("Art");
		return category;
    	
    }
	

}
