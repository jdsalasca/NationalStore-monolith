package com.shop.models;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documentyype")
public class DocumentType {

	@Id
	private String id;
	
	
    @Size(min = 5, max = 200, message 
    	      = "Default")
	private String type;


	public DocumentType(String id, @Size(min = 5, max = 200, message = "Default") String type) {
		super();
		this.id = id;
		this.type = type;
	}


	public DocumentType() {
		super();
	}


	public DocumentType(String id) {
		super();
		this.id = id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
    
    
    
}
