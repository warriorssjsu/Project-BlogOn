package com.warriors.blogOnProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comments_table")
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String commentdesc;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Blog blog;

}
