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

import com.shop.models.City;
import com.shop.repositories_dao.ICityDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/city")
public class CityController {
	
	@Autowired
	ICityDAO iCityDAO;
	
	@GetMapping
	public ResponseEntity<List<City>> findAllcities (){
		
		List<City> request = iCityDAO.findAll();
		if (request.isEmpty()) {
			return new ResponseEntity("Cities not found", HttpStatus.NOT_FOUND);
		}
		 	return new ResponseEntity<List<City>>(request, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> findCityById (@PathVariable String id) {
		
		Optional<City> city = iCityDAO.findById(id);
		if (city.isPresent()) {
			return new ResponseEntity<City>(city.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity("city not found", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity<City> createCity (@Valid @RequestBody City city, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(bindingResult.getAllErrors().toString() + "Please check the fiels",
					HttpStatus.BAD_REQUEST);
		}
		try {
			City citySave = iCityDAO.save(city);
			return new ResponseEntity<City>(citySave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("city not created", HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<City> updateCityById (@PathVariable String id, @Valid @RequestBody City city, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(bindingResult.getAllErrors().toString() + "Please check the fiels",
					HttpStatus.BAD_REQUEST);
		}
		try {
			city.setId(id);
			City citySaved = iCityDAO.save(city);
			return new ResponseEntity<City> (citySaved, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity("city not updated", HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCityById (@PathVariable String id) {
		try {
			iCityDAO.deleteById(id);
			return new ResponseEntity("city eliminated", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity("city not eliminated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
