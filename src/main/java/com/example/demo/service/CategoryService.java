package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category; 
import com.example.demo.repository.CategoryRepo;

@Service         // business logic implemented here in this service class
public class CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	public void createCategory(Category category) {
		categoryRepo.save(category);
	}
	
	
	
	public List<Category> listCategory(){
              return categoryRepo.findAll();
	}


	//updating  updatingCategory(editCategory)
	public void editCategory(int categoryId, Category updatecategory) {
		Category category = categoryRepo.getById(categoryId);  //a. get category by Id  through repo
		          category.setCategoryName(updatecategory.getCategoryName());
		          category.setDescription(updatecategory.getDescription());
		          category.setImageUrl(updatecategory.getImageUrl());
		          categoryRepo.save (category);                          //b.  save the updated category 
		          
	}


     //////????
	public boolean findById(int categoryId) {
		// TODO Auto-generated method stub
		//return false;
		return categoryRepo.findById(categoryId).isPresent();  //.isPresent() returns  true   or  false
	}
	
	////    read / find by Id    //method overloading with the method down below
	public Optional<Category> readCategory(Integer categoryId) {
		return categoryRepo.findById(categoryId);
	}
	
	
	//    read /find by name
	          public Category readCategory(String categoryName) {
		return categoryRepo.findByCategoryName(categoryName);
	}

	/**
	 * @Autowired
	private Categoryrepository categoryrepository;

	public List<Category> listCategories() {
		return categoryrepository.findAll();
	}

	public void createCategory(Category category) {
		categoryrepository.save(category);
	}

	public Category readCategory(String categoryName) {
		return categoryrepository.findByCategoryName(categoryName);
	}

	public Optional<Category> readCategory(Integer categoryId) {
		return categoryrepository.findById(categoryId);
	}

	public void updateCategory(Integer categoryID, Category newCategory) {
		Category category = categoryrepository.findById(categoryID).get();
		category.setCategoryName(newCategory.getCategoryName());
		category.setDescription(newCategory.getDescription());
		category.setImageUrl(newCategory.getImageUrl());
		categoryrepository.save(category);
	}
	 */


}
