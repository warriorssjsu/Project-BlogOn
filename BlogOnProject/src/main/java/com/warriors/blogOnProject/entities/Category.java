package com.warriors.blogOnProject.entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;



import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name="category_table")
public class Category {
	
	@Id
	@Column(name="categoryId")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long categoryId;
	
	@Column(name="name")
    private String name;
	
	/*@OneToMany(mappedBy = "category")
	private ArrayList blogs = new ArrayList();*/
	
	public Long getId() {
		return categoryId;
	}

	public void setId(Long id) {
		categoryId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
