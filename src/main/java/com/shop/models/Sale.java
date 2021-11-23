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
	private Client cliendId;
	
    @NotBlank
	private User userId;
    @Min (value = 5000, message = "min value must be 5000 COP")
	private float totalsale;
    @Min (value = 5000, message = "min value must be 5000 COP")
	private float saleTaxes;
    
	public Sale(String id, @Size(min = 3, max = 200, message = "Default") Client cliendId, @NotBlank User userId,
			@Min(value = 5000, message = "min value must be 5000 COP") float totalsale,
			@Min(value = 5000, message = "min value must be 5000 COP") float saleTaxes) {
		super();
		this.id = id;
		this.cliendId = cliendId;
		this.userId = userId;
		this.totalsale = totalsale;
		this.saleTaxes = saleTaxes;
	}
	public Sale(@Size(min = 3, max = 200, message = "Default") Client cliendId, @NotBlank User userId,
			@Min(value = 5000, message = "min value must be 5000 COP") float totalsale,
			@Min(value = 5000, message = "min value must be 5000 COP") float saleTaxes) {
		super();
		this.cliendId = cliendId;
		this.userId = userId;
		this.totalsale = totalsale;
		this.saleTaxes = saleTaxes;
	}
	public Sale(String id) {
		super();
		this.id = id;
	}
	public Sale() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Client getCliendId() {
		return cliendId;
	}
	public void setCliendId(Client cliendId) {
		this.cliendId = cliendId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
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
