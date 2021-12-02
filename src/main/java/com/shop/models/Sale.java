package com.shop.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sale")
public class Sale {
	
	@Id
	private String id;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private Client client_document;
    
    @Min (value = 5000, message = "min value must be 5000 COP")
	private float totalsale;
    @Min (value = 5000, message = "min value must be 5000 COP")
	private float saleTaxes;
	public Sale(String id, @Size(min = 3, max = 200, message = "Default") Client client_document,
			@Min(value = 5000, message = "min value must be 5000 COP") float totalsale,
			@Min(value = 5000, message = "min value must be 5000 COP") float saleTaxes) {
		super();
		this.id = id;
		this.client_document = client_document;
		this.totalsale = totalsale;
		this.saleTaxes = saleTaxes;
	}
	public Sale(@Size(min = 3, max = 200, message = "Default") Client client_document,
			@Min(value = 5000, message = "min value must be 5000 COP") float totalsale,
			@Min(value = 5000, message = "min value must be 5000 COP") float saleTaxes) {
		super();
		this.client_document = client_document;
		this.totalsale = totalsale;
		this.saleTaxes = saleTaxes;
	}
	public Sale() {
		super();
	}
	public Sale(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Client getClient_document() {
		return client_document;
	}
	public void setClient_document(Client client_document) {
		this.client_document = client_document;
	}
	public float getTotalsale() {
		return totalsale;
	}
	public void setTotalsale(float totalsale) {
		this.totalsale = totalsale;
	}
	public float getSaleTaxes() {
		return saleTaxes;
	}
	public void setSaleTaxes(float saleTaxes) {
		this.saleTaxes = saleTaxes;
	}
    
    
    
    
    
	
	
    
    
	
	
	
	
	

}
