package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.demo.dto.ProductDto;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
   
    @Autowired
    CategoryService categoryService;
    
    
    
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
    	                                   
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());     //optionalCategory is Optional object  used to represent null value will return a empty otional object with a   absent value 
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();  //optionalCategory is Optional object   then use get() to get the category oblect!
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(  new ApiResponse(true, "Product has been added"), HttpStatus.CREATED );
    }
    
    
    @GetMapping("/")    //get a list of products   //lists Products
    public ResponseEntity< List<ProductDto> > getProducts()
    {
        List<ProductDto> products =productService.getALlProducts();   //products here refer to the     list of ProductDto
        return new ResponseEntity<>(products , HttpStatus.OK);   //pass products into  the new ResponseEntity<>()       products: which is  a list<ProductDto>    (a list of ProductDto)
    	
    }
    
   
    //create an api to edit the product   updateproduct!
    @PostMapping ("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct( @PathVariable("productId")  Integer productId,   @RequestBody ProductDto productDto  ) throws Exception
    {
    	Optional<Category> optionalCategory= categoryService.readCategory(productDto.getCategoryId());      //public Optional<Category> readCategory(Integer categoryId) {return categoryRepo.findById(categoryId);
    	if ( ! optionalCategory.isPresent())
    	{                                             // ApiResponse
    		return new ResponseEntity<ApiResponse>( new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
    	}
    	
    	productService.updateProduct(productDto, productId);
    	return  new ResponseEntity<ApiResponse>  (new ApiResponse(true, "Product has been updated") ,  HttpStatus.OK);
    	
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

     
     
     public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }  
    **/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}