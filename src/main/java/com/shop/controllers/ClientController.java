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

import com.shop.models.City;
import com.shop.models.Client;
import com.shop.repositories_dao.IClientDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	
	
	@Autowired
	IClientDAO iClientDAO;
	
	@GetMapping
	public ResponseEntity<List<Client>> findAllClients (){
		
		List<Client> clients =  iClientDAO.findAll();
		if (clients.isEmpty()) {
			return new ResponseEntity("Clients not found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);	
	}
	@PostMapping
	public ResponseEntity<Client> createClient (@Valid @RequestBody Client client, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(bindingResult.getAllErrors().toString() + "Please check the fiels",HttpStatus.BAD_REQUEST);
			}
		try {
			Client ClientSave = iClientDAO.save(client);
			return new ResponseEntity<Client>(ClientSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("city not created", HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
