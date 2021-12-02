package com.shop.repositories_dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.models.SaleDetails;

public interface ISaleDetailsDAO extends MongoRepository<SaleDetails, String> {
	
	
	List<SaleDetails> findByIdSale ( String id);
	
	
}
