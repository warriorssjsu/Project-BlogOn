package com.warriors.blogOnProject.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.warriors.blogOnProject.entities.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

	@Autowired
    private UserRepository userRepository;
 
    @Test
    public void whenFindById_thenReturnUser() {
        // given
        User user = new User("abcd1234","David","David.Walker@hotmail.com","user");
        userRepository.save(user);    
        // when
        Optional<User> found = userRepository.findById("abcd1234");
        
     
        assertEquals(found.get().getName(), user.getName());
        userRepository.deleteById(found.get().getId());
    }
    
    @Test
    public void whenFindAll_thenReturnUsers() {
          
        List<User> users = userRepository.findAll();
     
        assertNotNull(users);
    }
    

}
