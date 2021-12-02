package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.models.Product;
import com.shop.repositories_dao.IProductDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	IProductDAO iProductDAO;
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<Product>> findAllProducts () {
		List<Product> reponse = iProductDAO.findAll();
		if (reponse.isEmpty()) {
			return new ResponseEntity("products not found", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Product>>(reponse, HttpStatus.OK);
		}
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping ("/{id}")
	public ResponseEntity<Product> findProductById (@PathVariable String id) {
		
		Optional<Product> productFound = iProductDAO.findById(id);
		if (productFound.isEmpty()) {
			return new ResponseEntity("product don't found", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Product> (productFound.get(), HttpStatus.OK);
		}
		
		
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/name/{name}")
	public ResponseEntity<Product> findProductsByName (@PathVariable String name){
		
		List<Product> productsFound = iProductDAO.findByProductNameLike(name);
		if (productsFound.isEmpty()) {
			return new ResponseEntity("we don't found any product", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Product> (productsFound.get(0), HttpStatus.OK);
		}
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/code/{productCode}")
	public ResponseEntity <Product> findByProductCode (@PathVariable String productCode){
		
		List<Product> productsFound  = iProductDAO.findByProductCode(productCode);
		if  (productCode.isEmpty()) {
			return new ResponseEntity("products not found", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Product> (productsFound.get(0), HttpStatus.OK);
		}
		
	}
	@PreAuthorize("hasRole('USER')")	
	@PostMapping
	public ResponseEntity<Product> createProduct  (@Valid @RequestBody Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		}
		try {
			Product productSaved = iProductDAO.save(product);
			return new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);
		} catch (Exception e) {

		return new ResponseEntity("We couldn't create the product", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PreAuthorize("hasRole('USER')")	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProductById (@PathVariable String id, @Valid @RequestBody Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("please check all the fields", HttpStatus.BAD_REQUEST);
		}
		Optional<Product> productFound = iProductDAO.findById(id);
		if (productFound.isPresent()) {
			product.setId(id);
			Product productSaved = iProductDAO.save(product);
			return new ResponseEntity<Product>(productSaved, HttpStatus.OK);
		}else {
			return new ResponseEntity("we don't found the product", HttpStatus.NOT_FOUND);
		}
	}
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteProductById (@PathVariable String id) {
		
		try {
			iProductDAO.deleteById(id);
			return new ResponseEntity("product deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("product not found", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	

}
