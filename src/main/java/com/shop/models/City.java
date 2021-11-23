package com.shop.models;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "city")
public class City {

	
	@Id
	private String id;
	
    @Size(min = 5, max = 200, message 
    	      = "Default")
	private String name;

	public City(String id, @Size(min = 5, max = 200, message = "Default") String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	public City(@Size(min = 5, max = 200, message = "Default") String name) {
		super();
		this.name = name;
	}


	public City() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
    
    

	
	
	
}
