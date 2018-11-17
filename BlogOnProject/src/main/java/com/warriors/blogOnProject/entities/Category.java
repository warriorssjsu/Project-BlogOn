package com.warriors.blogOnProject.entities;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="category_table")
public class Category {
	
	@Id
	@Column(name="categoryId")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@NonNull
	@Column(name="name")
    private String name;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
    private User user;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Blog> blogs;
    
}
