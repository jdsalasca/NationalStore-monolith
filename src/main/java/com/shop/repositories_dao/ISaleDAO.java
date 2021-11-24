package com.shop.repositories_dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.models.Sale;

public interface ISaleDAO  extends MongoRepository<Sale, String>{

	
	List<Sale> findAll();
	
}
