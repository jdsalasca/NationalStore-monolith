package com.shop.repositories_dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.models.DocumentType;

public interface IDocumentTypeDAO extends MongoRepository<DocumentType, String>{

	
	List<DocumentType> findAll();
	
	Optional<DocumentType> findById(String id);
	
	
	

}
