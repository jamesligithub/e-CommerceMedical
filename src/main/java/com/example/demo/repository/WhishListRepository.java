package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.model.WishList;



@Repository
public interface WhishListRepository extends JpaRepository<WishList ,Integer> {

	
	List<WishList> findAllByUserOrderByCreatedDateDesc(User user); //what the hell that is brilliant !!!!!!!!!!!  find all return a list of whishlist
}
