package com.warriors.blogOnProject.dao;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.warriors.blogOnProject.entities.Category;


@Repository
//@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long>{
     
	Category findByName(String name);
	List<Category> findAllByUserId(String id);
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
