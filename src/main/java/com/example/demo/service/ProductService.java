package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.exceptions.ProductNotExistsException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;




@Service
public class ProductService {

@Autowired
    private ProductRepository productRepository;

  ////////add products
  public void addProduct(ProductDto productDto, Category category) 
  {
    Product product = getProductFromDto(productDto, category);//we created (getProductFromDto  method) it returns a product  see below
    
    productRepository.save(product);
  }

     
     public static Product getProductFromDto(ProductDto productDto, Category category) { //getProductFromDto  method
      Product product = new Product();
      product.setCategory(category);//set  category which we will get it from the parameter
      product.setDescription(productDto.getDescription());
      product.setImageURL(productDto.getImageURL());
      product.setPrice(productDto.getPrice());
      product.setName(productDto.getName());
      return product;
      }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////get all product
	public List<ProductDto> getALlProducts() {
		// TODO Auto-generated method stub
		List<Product> allProducts=productRepository.findAll();     //Returns all instances of the type.Specified by: findAll() in CrudRepositoryReturns:all entities
		
		List<ProductDto>  productDtos = new ArrayList<ProductDto>();     // create a List    arraylist    to contain  productDtos
		for (Product product : allProducts)
		{
			productDtos.add( getProductDto(product) ); //getProductDto(product)  usiing the method below   getProductDto(Product product)  see below
		}
		
		return productDtos;
	}
   
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void updateProduct(ProductDto productDto,   Integer productId)   throws Exception {
		
		Optional<Product>   optionalproduct = productRepository.findById(productId);
		//throw an exception if product doesn't exist
		if ( !optionalproduct.isPresent())  // if not present  then ---
		{
			throw new Exception("product not present");
		}
		
		Product product=optionalproduct.get() ;                    //.get() If a value is present, returns the value, otherwise throws NoSuchElementException.Returns:the non-null value described by this OptionalThrows:NoSuchElementException - if no value is present
		
	      //////product.setCategory(category);//set  category which we will get it from the parameter
	      product.setDescription(productDto.getDescription());
	      product.setImageURL(productDto.getImageURL());
	      product.setPrice(productDto.getPrice());
	      product.setName(productDto.getName());
	      
	      productRepository.save(product);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
//////////////////////////////this method is used in cartService//----/CartService   findbyId   optionalproducr is empty   then throw productNotExistsException////////////////////////////////////////////////////////////////////////////////////////////////
	public Product findById(Integer productId) {
		// TODO Auto-generated method stub
		Optional<Product> optionalProduct= productRepository.findById(productId);
		if (  optionalProduct.isEmpty())//If a value is not present, returns true, otherwise false.Returns:true if a value is not present, otherwise false
	    {
			throw  new ProductNotExistsException("product is invalid:" +productId);
	    }
		return optionalProduct.get();
	}

///////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
 * public class ProductDto {
	
	private Integer id;  //it is optional not requred//  for create it can be optional //  for update we need it
	private @NotNull String name;
	private @NotNull String imageURL;
	private @NotNull double price;
	private @NotNull String description;
	private @NotNull Integer categoryId;  // category Id
 */


}