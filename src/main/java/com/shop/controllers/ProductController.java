package com.shop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping
	public ResponseEntity<List<Product>> findAllProducts () {
		List<Product> reponse = iProductDAO.findAll();
		if (reponse.isEmpty()) {
			return new ResponseEntity("products not found", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Product>>(reponse, HttpStatus.OK);
		}
	}
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

}
