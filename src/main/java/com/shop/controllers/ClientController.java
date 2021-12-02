package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.models.City;
import com.shop.models.Client;
import com.shop.models.User;
import com.shop.repositories_dao.IClientDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	
	
	@Autowired
	IClientDAO iClientDAO;
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<Client>> findAllClients (){
		
		List<Client> clients =  iClientDAO.findAll();
		if (clients.isEmpty()) {
			return new ResponseEntity("Clients not found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);	
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping ("/{id}")
	public ResponseEntity<Client> findClientById (@PathVariable String id) {
		
		Optional<Client> client = iClientDAO.findById(id);
		if (client.isPresent()) {
			return new ResponseEntity<Client>(client.get(),HttpStatus.OK);
			}else {
				return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
			}
				
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/document/{identityDocument}")
	public ResponseEntity<Client> findClientByDocument (@PathVariable String idDocument) {
		
		List<Client> clientFound = iClientDAO.findByDocumentnumber(idDocument);
		if (!clientFound.isEmpty()) {
			return new ResponseEntity<Client>(clientFound.get(0), HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PreAuthorize("hasRole('USER')")	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteClientById (@PathVariable String id){
			
		try {
			iClientDAO.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("we don't find client with that id", HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasRole('USER')")
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
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClientById (@PathVariable String id, @Valid @RequestBody Client client, BindingResult bindingResult){
		if ( bindingResult.hasErrors()) {
			return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		}
		Optional<Client> clientFound  = iClientDAO.findById(id);
		if (clientFound.isPresent()) {
			client.setId(id);
			Client clientSaved = iClientDAO.save(client);
			return new ResponseEntity<Client> (clientSaved, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Client> (HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	

}
