package com.warriors.blogOnProject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.warriors.blogOnProject.entities.Category;


@Repository
public class CategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void createCategory(Category category) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			System.out.println("sessionFactory not null");
			session.beginTransaction();
			Integer id = (Integer) session.save(category);
			System.out.println("Category is created with ID: "+id);
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("In catch printing stack trace");
			e.printStackTrace();
		}
		
	}
	

}
