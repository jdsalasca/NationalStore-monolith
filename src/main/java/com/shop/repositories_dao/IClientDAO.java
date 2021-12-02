package com.shop.repositories_dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.models.Client;

public interface IClientDAO extends MongoRepository<Client, String> {

	List<Client> findAll();
	
	Optional<Client> findById();
	
	List<Client> findByDocumentnumber(String documentnumber);
	
	
	
}
