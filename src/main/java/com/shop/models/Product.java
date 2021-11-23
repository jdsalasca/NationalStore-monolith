package com.shop.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {

	
	@Id
	private String id;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private float ivaTosell;
    @Size(min = 3, max = 200, message 
      = "Default")
	private String name;
	
	 @Min(value = 1000, message = "Price should be more than 1000 COP")
	private float priceItem;

	public Product(String id, @Size(min = 3, max = 200, message = "Default") float ivaTosell,
			@Size(min = 3, max = 200, message = "Default") String name,
			@Min(value = 1000, message = "Price should be more than 1000 COP") float priceItem) {
		super();
		this.id = id;
		this.ivaTosell = ivaTosell;
		this.name = name;
		this.priceItem = priceItem;
	}
	

	public Product(@Size(min = 3, max = 200, message = "Default") float ivaTosell,
			@Size(min = 3, max = 200, message = "Default") String name,
			@Min(value = 1000, message = "Price should be more than 1000 COP") float priceItem) {
		super();
		this.ivaTosell = ivaTosell;
		this.name = name;
		this.priceItem = priceItem;
	}
	


	public Product(String id) {
		super();
		this.id = id;
	}


	public Product() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public float getIvaTosell() {
		return ivaTosell;
	}


	public void setIvaTosell(float ivaTosell) {
		this.ivaTosell = ivaTosell;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getPriceItem() {
		return priceItem;
	}


	public void setPriceItem(float priceItem) {
		this.priceItem = priceItem;
	}
	
	
	 



	
	
	
	
	
}
