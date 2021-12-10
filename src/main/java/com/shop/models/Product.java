package com.shop.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {

	
	@Id
	private String id;
	
	private float iva_item;

	private String productCode;

    @Size(min = 3, max = 200, message 
      = "Default")
	private String productName;
    
    private String nitSupplier;
	
	
	private float pricePurchase;
	 
	private float priceToBuy;
	 
	 
	 

	
	
	public Product(@Min(value = 1000, message = "Price should be more than 1000 COP") float iva_item,
			@Size(min = 3, max = 200, message = "Default") String productCode,
			@Size(min = 3, max = 200, message = "Default") String productName, String nitSupplier,
			@Min(value = 1000, message = "Price should be more than 1000 COP") float pricePurchase,
			@Min(value = 1000, message = "Price should be more than 1000 COP") float priceToBuy) {
		super();
		this.iva_item = iva_item;
		this.productCode = productCode;
		this.productName = productName;
		this.nitSupplier = nitSupplier;
		this.pricePurchase = pricePurchase;
		this.priceToBuy = priceToBuy;
	}
	public Product(String id, @Min(value = 1000, message = "Price should be more than 1000 COP") float iva_item,
			@Size(min = 3, max = 200, message = "Default") String productCode,
			@Size(min = 3, max = 200, message = "Default") String productName, String nitSupplier,
			@Min(value = 1000, message = "Price should be more than 1000 COP") float pricePurchase,
			@Min(value = 1000, message = "Price should be more than 1000 COP") float priceToBuy) {
		super();
		this.id = id;
		this.iva_item = iva_item;
		this.productCode = productCode;
		this.productName = productName;
		this.nitSupplier = nitSupplier;
		this.pricePurchase = pricePurchase;
		this.priceToBuy = priceToBuy;
	}

	public Product() {
		super();
	}
	public Product(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getIva_item() {
		return iva_item;
	}
	public void setIva_item(float iva_item) {
		this.iva_item = iva_item;
	}
	
	

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getNitSupplier() {
		return nitSupplier;
	}
	public void setNitSupplier(String nitSupplier) {
		this.nitSupplier = nitSupplier;
	}
	public float getPricePurchase() {
		return pricePurchase;
	}
	public void setPricePurchase(float pricePurchase) {
		this.pricePurchase = pricePurchase;
	}
	public float getPriceToBuy() {
		return priceToBuy;
	}
	public void setPriceToBuy(float priceToBuy) {
		this.priceToBuy = priceToBuy;
	}


	
	 



	
	
	
	
	
}
