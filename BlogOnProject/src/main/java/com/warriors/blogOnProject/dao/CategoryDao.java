package com.warriors.blogOnProject.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.warriors.blogOnProject.entities.Category;


@Repository
//@Transactional
public interface CategoryDao extends JpaRepository<Category, Long>{
     
	
	/*@Autowired
	private SessionFactory sessionFactory;
	
	public void createCategory(@Lazy Category category) {
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
		
	}*/
	

}
