package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.models.DocumentType;
import com.shop.repositories_dao.IDocumentTypeDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/documenttype")
public class DocumentTypeController {
	
	@Autowired
	IDocumentTypeDAO iDocumentTypeDAO;

	@GetMapping
	public ResponseEntity<List<DocumentType>> findAllDocumentType (){
		
		List<DocumentType> resultados =  iDocumentTypeDAO.findAll();
		
		if(resultados.isEmpty()) {
			
			return new ResponseEntity("documents not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<DocumentType>>(resultados, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DocumentType> findDocumentById (@PathVariable String id) {
		
		Optional<DocumentType> document = iDocumentTypeDAO.findById(id);
		if (document.isPresent()) {
			return new ResponseEntity<DocumentType>(document.get(), HttpStatus.OK);
		}
		return new ResponseEntity("documento no encontrado", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<DocumentType> newDocument (@Valid @RequestBody DocumentType documentType, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(bindingResult.getAllErrors().toString() + "Please check the fiels",
					HttpStatus.BAD_REQUEST);
		}
		try {
			DocumentType documentsave = iDocumentTypeDAO.save(documentType);
			return new ResponseEntity<DocumentType>(documentsave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("Documento no creado", HttpStatus.BAD_REQUEST);
					
		}
		
		
		
		
	}
}
