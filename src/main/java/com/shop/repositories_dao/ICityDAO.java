package com.shop.repositories_dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.models.City;

public interface ICityDAO extends MongoRepository<City, String>{
	
	List<City> findAll();
	
	Optional<City> findById(String id);
	
	

	
}
