package com.shop.models;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "supplier")
public class Supplier {
	
	@Id
	private String id;
	private String city;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private String address;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private String name;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private String phoneNumber;

	private String nit;
    
    
	public Supplier(String id, String city, @Size(min = 3, max = 200, message = "Default") String address,
			@Size(min = 3, max = 200, message = "Default") String name,
			@Size(min = 3, max = 200, message = "Default") String phoneNumber,
			@Size(min = 3, max = 200, message = "Default") String nit) {
		super();
		this.id = id;
		this.city = city;
		this.address = address;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.nit = nit;
	}


	public Supplier(String city, @Size(min = 3, max = 200, message = "Default") String address,
			@Size(min = 3, max = 200, message = "Default") String name,
			@Size(min = 3, max = 200, message = "Default") String phoneNumber,
			@Size(min = 3, max = 200, message = "Default") String nit) {
		super();
		this.city = city;
		this.address = address;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.nit = nit;
	}


	public Supplier(String id) {
		super();
		this.id = id;
	}


	public Supplier() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getNit() {
		return nit;
	}


	public void setNit(String nit) {
		this.nit = nit;
	}
	
	
    
    
    
    

}
