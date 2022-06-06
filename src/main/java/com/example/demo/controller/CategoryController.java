package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiresponse.ApiResponse;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;



@RestController
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/create")
	public   ResponseEntity<ApiResponse>   createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);    // then inside the --categoryService.createCategory-- method   categoryRepo.save(category) is called 
		//return "success";
		return new ResponseEntity<>(new ApiResponse(true,"A new categorysuccessfully created "),   HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/list")
	public List<Category> listCategory()
	{
		      return categoryService.listCategory();
	}
	
	
	
	@PostMapping("/update/{categoryId}")           // add validation check   if categoryId   does not exist    returns a error message that :the ID doesn't exist
	public   ResponseEntity<ApiResponse>    updateCategory( @PathVariable("categoryId") int categoryId ,   @RequestBody Category category)
	{
		System.out.println("category id " + categoryId);
		if ( ! categoryService.findById(categoryId))              // if not  ! not
		{
			return  new ResponseEntity<>( new ApiResponse(false, "category does not exist") , HttpStatus.NOT_FOUND );  //Display error message category does not exist"
		}
		
		categoryService.editCategory(categoryId, category);
		//return "category has been updated";
		return    new ResponseEntity<>( new ApiResponse(true, "category has been updated") , HttpStatus.OK );

	}
	

}
