package com.shop.repositories_dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.models.Supplier;

public interface ISupplierDAO  extends MongoRepository<Supplier, String>{

	List<Supplier> findAll();
	
	List<Supplier> findByNit(String nit);
	
}
