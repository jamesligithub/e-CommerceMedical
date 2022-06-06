package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.WishList;
import com.example.demo.repository.WhishListRepository;

@Service
public class WhishListService {
	
	@Autowired
	WhishListRepository whishListRepository;
	
	@Autowired
	ProductService productService;    //autowired  ProductService   to use  " public ProductDto getProductDto(Product product) "

	
	public void createWishList(WishList wishList) {
		// TODO Auto-generated method stub
		whishListRepository.save(wishList);
	}

	
	
	
	
/////////////////////////////////////////// method from productService.getProductDto////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 *  
	 public ProductDto getProductDto(Product product) {   //getProductDto(Product product)  transform product to productDto!
	ProductDto productDto = new ProductDto();
	productDto.setDescription(product.getDescription());
	productDto.setImageURL(product.getImageURL());
	productDto.setName(product.getName());
	productDto.setCategoryId( product.getCategory().getId() );   //product.getCategory()  obtain the category_obj   then .getId()
	productDto.setPrice(product.getPrice());
	productDto.setId(product.getId());
	return productDto;
	}
	 * 
	 */
	public List<ProductDto> getWishListForUser(User user) {
		// TODO Auto-generated method stub
		final  List<WishList>  wishLists = whishListRepository.findAllByUserOrderByCreatedDateDesc(user);// find all the wishlist object (wishLists) that created by a same user   and order those wishlist object according o the time
		// for each wishlist objects we find out the  productdto and send it back 
		List<ProductDto> productDtos= new ArrayList<>();
		for(WishList x : wishLists)   //x is an wishList object
		{
			productDtos.add(  productService.getProductDto( x.getProduct())  );    ////autowired  ProductService   to use  " public ProductDto getProductDto(Product product) "  ///first get the productDTo then add the productDto to  productDto lists
		}
		return productDtos;  // return a list of productDto objects!!!!!!!!!
	}
	
	

}
