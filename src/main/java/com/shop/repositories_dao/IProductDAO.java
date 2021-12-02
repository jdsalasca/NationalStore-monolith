package com.shop.repositories_dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.models.Product;

public interface IProductDAO extends MongoRepository<Product, String>{
	
	Optional<Product> findById( String id);

	
	
	List <Product> findByProductNameLike (String name);
	
	
	List <Product> findByProductCode (String name);
}
