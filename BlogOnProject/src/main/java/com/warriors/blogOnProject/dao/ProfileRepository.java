package com.warriors.blogOnProject.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.warriors.blogOnProject.entities.Profile;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, String> {

	
	
	
}
