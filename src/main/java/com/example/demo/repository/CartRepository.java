package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cart;
import com.example.demo.model.User;

public interface CartRepository extends JpaRepository < Cart, Integer> {

	List<Cart>  findAllByUserOrderByCreatedDateDesc(User user); //  customized  find all Cart object  By user and order them by created date in Descending order

}
