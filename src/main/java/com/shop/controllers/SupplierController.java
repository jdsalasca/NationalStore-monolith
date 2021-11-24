package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.models.Supplier;
import com.shop.repositories_dao.ISupplierDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
	
	@Autowired
	ISupplierDAO iSupplierDAO;
	
	@GetMapping
	public ResponseEntity<List<Supplier>> findallSuppliers () {
		
		List<Supplier> response = iSupplierDAO.findAll();
		
		if (response.isEmpty()) {
			return new ResponseEntity("we don't found Suppliers", HttpStatus.NOT_FOUND);
			
		}else {
			return new ResponseEntity<List<Supplier>>(response, HttpStatus.OK);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Supplier> findUserById(@PathVariable String id) {
		
		Optional<Supplier> supplierfound = iSupplierDAO.findById(id);
		if (supplierfound.isPresent()) {
			return new ResponseEntity<Supplier>(supplierfound.get(), HttpStatus.OK);
		}
		return new ResponseEntity("Supplier not found", HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping
	public ResponseEntity<Supplier> createSupplier (@Valid @RequestBody Supplier supplier, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
		return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		}
		try {
			Supplier supplierSaved = iSupplierDAO.save(supplier);
			return new ResponseEntity<Supplier>(supplierSaved,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("We couldn't create the supplier", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Supplier> updateSupplierById (@PathVariable String id, @Valid @RequestBody Supplier supplier, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		}
		try {
			supplier.setId(id);
			Supplier supplierUpdated = iSupplierDAO.save(supplier);
			return new ResponseEntity<Supplier>(supplierUpdated, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity("we couldn't update the supplier, sorry", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteSupplierById (@PathVariable String id){
		try {
			iSupplierDAO.deleteById(id);
			return new ResponseEntity("supplier deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("supplier not found", HttpStatus.BAD_REQUEST);
		}
	}
		
		
		
	
	



}
