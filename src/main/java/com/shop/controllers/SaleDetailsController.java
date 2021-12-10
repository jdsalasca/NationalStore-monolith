package com.shop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.models.SaleDetails;
import com.shop.repositories_dao.ISaleDetailsDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("api/saleDetail")
public class SaleDetailsController {
	
	@Autowired
	ISaleDetailsDAO iSaleDetailsDAO;
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<SaleDetails>> findAllSaleDetails () {
		List<SaleDetails> saleDetails =  iSaleDetailsDAO.findAll();
		if (saleDetails.isEmpty()) {
			return new  ResponseEntity("SaleDetails not found", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<SaleDetails>>(saleDetails, HttpStatus.OK);
		}
		
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{idSale}")
	public ResponseEntity<List<SaleDetails>> findSaleDetailsByIdSale (@PathVariable String id) {
		
		List<SaleDetails> salesfounded  = iSaleDetailsDAO.findByIdSale(id);
		if (salesfounded.isEmpty()) {
			return new ResponseEntity("Sales not found", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<SaleDetails>>(salesfounded, HttpStatus.OK);
		}
	}
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<SaleDetails> newSaleDetail (@PathVariable String id, @Valid @RequestBody SaleDetails saleDetails, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("please check all the fields", HttpStatus.BAD_REQUEST);
		}
		try {
			saleDetails.setId(id);
			SaleDetails saleDetailsToSave = iSaleDetailsDAO.save(saleDetails);
			return new ResponseEntity<SaleDetails>(saleDetailsToSave, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Sale Details not saved, sorry", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSaleDetailById (@PathVariable String id) {
		try {
			iSaleDetailsDAO.deleteById(id);
			return new ResponseEntity("Sale Details Deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("we couldn't find the id", HttpStatus.NOT_FOUND);
		}
		
		
		
		
	}
	
	
}
