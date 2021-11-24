package com.shop.repositories_dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.models.Product;

public interface IProductDAO extends MongoRepository<Product, String>{

	
}
