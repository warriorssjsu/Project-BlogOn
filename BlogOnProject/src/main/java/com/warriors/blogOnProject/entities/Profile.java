package com.warriors.blogOnProject.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="profile_table")
public class Profile {
	
	
	
		@Id
		private String id;
		private String displayName;	
		private String dob;
		private String secEmail;
		private String address;
		
				
		/*@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	    private Set<Blog> blogs;*/
	}

