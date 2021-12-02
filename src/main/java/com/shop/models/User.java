package com.shop.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	
	@Id
	private String id;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String name;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String nick;
    @Email
	private String email;
    

    @Size(min = 3, max = 200, message 
  	      = "Default")
	private String identityDocument;
    @Size(min = 5, max = 200, message 
  	      = "Default")
	private String password;
    
	private Set<String> roles = new HashSet<>();
	
	public void addUserRol () {
		
		roles.add("ROLE_USER");
		
	}
	
	

	public User(String id, @Size(min = 3, max = 200, message = "Default") String name,
			@Size(min = 3, max = 200, message = "Default") String nick, @Email String email, City city,
			@Size(min = 3, max = 200, message = "Default") String identityDocument,
			@Size(min = 5, max = 200, message = "Default") String password, Set<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.nick = nick;
		this.email = email;
		this.identityDocument = identityDocument;
		this.password = password;
		this.roles = roles;
	}

	public User(@Size(min = 3, max = 200, message = "Default") String name,
			@Size(min = 3, max = 200, message = "Default") String nick, @Email String email, City city,
			@Size(min = 3, max = 200, message = "Default") String identityDocument,
			@Size(min = 5, max = 200, message = "Default") String password, Set<String> roles) {
		super();
		this.name = name;
		this.nick = nick;
		this.email = email;
		this.identityDocument = identityDocument;
		this.password = password;
		this.roles = roles;
	}

	public User() {
		super();
	}

	public User(String id) {
		super();
		this.id = id;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	



	
	

}
