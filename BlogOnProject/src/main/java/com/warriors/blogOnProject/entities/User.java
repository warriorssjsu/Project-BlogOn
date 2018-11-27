package com.warriors.blogOnProject.entities;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_table")
public class User {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;	
	private String name;	
	//private String role;
	private String email;
	
	
	/*@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Blog> blogs;*/
}
