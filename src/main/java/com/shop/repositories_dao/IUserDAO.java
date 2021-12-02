package com.shop.repositories_dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.models.User;

public interface IUserDAO extends MongoRepository<User, String>{
	List<User> findAll();
	
	Optional<User> findById(String id);
	
	List<User> findByNick (String nick);
	List<User> findByEmail (String email);
	List<User> findByIdentityDocument(String identityDocument);
}
