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

import com.shop.models.Sale;
import com.shop.repositories_dao.ISaleDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sale")
public class SaleController {

	@Autowired
	ISaleDAO iSaleDAO;
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<Sale>> findAllSales() {
		List<Sale> response = iSaleDAO.findAll();
		if (response.isEmpty()) {
			return new ResponseEntity("we don't found Sales", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Sale>>(response, HttpStatus.OK);
		}
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable String id) {
		Optional<Sale> saleFound = iSaleDAO.findById(id);
		if (saleFound.isPresent()) {
			return new ResponseEntity<Sale>(saleFound.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity("Sale not found", HttpStatus.NOT_FOUND);
		}
		
		
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ResponseEntity<Sale> createSale(@Valid @RequestBody Sale sale, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		}
		try {
			Sale saleSaved = iSaleDAO.save(sale);
			return new ResponseEntity<Sale>(saleSaved, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("we couldn't create the sale", HttpStatus.BAD_REQUEST);
		}

	}
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<Sale> updateSaleById(@PathVariable String id, @Valid @RequestBody Sale sale, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		}
		try {
			sale.setId(id);
			Sale saleUpdated = iSaleDAO.save(sale);
			return new ResponseEntity<Sale>(saleUpdated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("we couldn't update the resource, sorry!", HttpStatus.BAD_REQUEST);
		}
		
	}
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping ("/{id}")
	public ResponseEntity<HttpStatus> deleteSaleById (@PathVariable String id){
		try {
			iSaleDAO.deleteById(id);
			return new ResponseEntity("Sale deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("we didn't found the Sale",HttpStatus.NOT_FOUND);
		}
	}

}
