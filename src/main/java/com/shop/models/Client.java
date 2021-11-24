package com.shop.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class Client {
	
	@Id
	private String id;
	
	
	
	private DocumentType documentType;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String documentnumber;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String address;
	
	
    @Size(min = 3, max = 200, message 
    	      = "Default")
	@Email
	private String email;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String name;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String phonenumber;
    
	private City city;
	
	
	

	public Client() {
		super();
	}

	public Client(@NotBlank DocumentType documentType,
			@Size(min = 3, max = 200, message = "Default") String documentnumber,
			@Size(min = 3, max = 200, message = "Default") String address,
			@Size(min = 3, max = 200, message = "Default") @Email String email,
			@Size(min = 3, max = 200, message = "Default") String name,
			@Size(min = 3, max = 200, message = "Default") String phonenumber, City city) {
		super();
		this.documentType = documentType;
		this.documentnumber = documentnumber;
		this.address = address;
		this.email = email;
		this.name = name;
		this.phonenumber = phonenumber;
		this.city = city;
	}

	public Client(String id, @NotBlank DocumentType documentType,
			@Size(min = 3, max = 200, message = "Default") String documentnumber,
			@Size(min = 3, max = 200, message = "Default") String address,
			@Size(min = 3, max = 200, message = "Default") @Email String email,
			@Size(min = 3, max = 200, message = "Default") String name,
			@Size(min = 3, max = 200, message = "Default") String phonenumber, City city) {
		super();
		this.id = id;
		this.documentType = documentType;
		this.documentnumber = documentnumber;
		this.address = address;
		this.email = email;
		this.name = name;
		this.phonenumber = phonenumber;
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentnumber() {
		return documentnumber;
	}

	public void setDocumentnumber(String documentnumber) {
		this.documentnumber = documentnumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	
	
	
	
	
	
	
	

}
