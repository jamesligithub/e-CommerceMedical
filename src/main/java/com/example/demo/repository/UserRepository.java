package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;



//model //primary  key type
public interface UserRepository extends JpaRepository< User, Integer>{

	//return
	    User   findByEmail (String Email);  //customized UserRepository method

	

		//boolean exists(User user);

		
		
}
