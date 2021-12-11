package com.shop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.json.JsonObject;
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

import org.json.JSONObject;

import com.shop.models.Max_code;
import com.shop.models.Sale;
import com.shop.models.SaleDetails;
import com.shop.models.SalesByClient;
import com.shop.repositories_dao.IClientDAO;
import com.shop.repositories_dao.ISaleDAO;
import com.shop.repositories_dao.ISaleDetailsDAO;
import com.shop.repositories_dao.IUserDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sale")
public class SaleController {

	@Autowired
	ISaleDAO iSaleDAO;
	
	@Autowired
	ISaleDetailsDAO iSaleDetailsDAO;
	
	@Autowired
	IClientDAO iClientDao;
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<Sale>> findAllSales() {
		List<Sale> response = iSaleDAO.findAll();
		if (response.isEmpty()) {
			return new ResponseEntity("we don't found Sales", HttpStatus.NOT_FOUND);
		} else {
			for (Sale o: response) {
				o.setSaleDetails(iSaleDetailsDAO.findByIdSale(o.getId()));	
				
			}
			return new ResponseEntity<List<Sale>>(response, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/maxcode")
	public ResponseEntity<List<Sale>> findMaxCode () {
		
		List <Sale> response = iSaleDAO.findAll();
		if (response.isEmpty()) {
			return new ResponseEntity("we don't found Sales", HttpStatus.NOT_FOUND);
		} else {
			int max_code = Integer.valueOf(response.get(response.size()-1).getSale_code()) + 1;
			
			
			Max_code biggest_num = new Max_code(String.valueOf(max_code));
			
			
			return new ResponseEntity(Integer.valueOf(biggest_num.getConsecutivo()), HttpStatus.OK);
		}
		
				
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable String id) {
		Optional<Sale> saleFound = iSaleDAO.findById(id);
		if (saleFound.isPresent()) {
			saleFound.get().setSaleDetails(iSaleDetailsDAO.findByIdSale(saleFound.get().getId()));
			return new ResponseEntity<Sale>(saleFound.get(), HttpStatus.OK);
		}else {
				
			return new ResponseEntity("Sale not found", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/totalSales")
	public ResponseEntity<List<SalesByClient>> findtotalSalesByClientDocument () {
		List <Sale> response = iSaleDAO.findAll();
		List<String> documents = new ArrayList();
		List<SalesByClient> salesPerClient = new ArrayList();
		if (response.isEmpty()) {
			return new ResponseEntity("we don't found Sales", HttpStatus.NOT_FOUND);
		} else {
			for (Sale o: response) {
				if (documents.isEmpty()) {
					documents.add(o.getClientDocument());
				}
				if (!documents.contains(o.getClientDocument())){
					documents.add(o.getClientDocument());	
				}
			}
			for (String o: documents) {	
				float totalspentByClient = 0;
				String name = "USER_Name_Not_Registed";
				
				if (!iClientDao.findByDocumentnumber(o).isEmpty()) {
					name  = iClientDao.findByDocumentnumber(o).get(0).getName();		
				}
				List<Sale> salesByClient = iSaleDAO.findAllByClientDocument(o);
				for (Sale sale: salesByClient) {
					totalspentByClient += sale.getTotal_venta();
				}
				SalesByClient clientSaved = new SalesByClient(o, name, totalspentByClient);
				salesPerClient.add(clientSaved);				
			}
			return new ResponseEntity(salesPerClient, HttpStatus.OK);
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
			List <SaleDetails> listOfDetails = saleSaved.getSaleDetails();
					for (SaleDetails o : listOfDetails) {
						o.setIdSale(saleSaved.getId());				
					}
					iSaleDetailsDAO.saveAll(listOfDetails);
			
			return new ResponseEntity(listOfDetails, HttpStatus.OK);
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
			List <SaleDetails> listOfDetails = saleUpdated.getSaleDetails();
			for (SaleDetails o : listOfDetails) {
				o.setIdSale(saleUpdated.getId());
				iSaleDetailsDAO.save(o);
				
				
			}
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
