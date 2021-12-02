package com.shop.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saledetails")
public class SaleDetails {
	
	@Id
	private String id;
	
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private String productCode;
    
	
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private String idSale;

    @Size(min = 3, max = 200, message 
    	      = "Default")
	private int productQuantity;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private float saleValue;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private float ivaValue;

	public SaleDetails(String id) {
		super();
		this.id = id;
	}
	
	
	public SaleDetails() {
		super();
	}


	public SaleDetails(@Size(min = 3, max = 200, message = "Default") String productCode,
			@Size(min = 3, max = 200, message = "Default") String idSale,
			@Size(min = 3, max = 200, message = "Default") int productQuantity,
			@Size(min = 3, max = 200, message = "Default") float saleValue,
			@Size(min = 3, max = 200, message = "Default") float ivaValue) {
		super();
		this.productCode = productCode;
		this.idSale = idSale;
		this.productQuantity = productQuantity;
		this.saleValue = saleValue;
		this.ivaValue = ivaValue;
	}


	public SaleDetails(String id, @Size(min = 3, max = 200, message = "Default") String productCode,
			@Size(min = 3, max = 200, message = "Default") String idSale,
			@Size(min = 3, max = 200, message = "Default") int productQuantity,
			@Size(min = 3, max = 200, message = "Default") float saleValue,
			@Size(min = 3, max = 200, message = "Default") float ivaValue) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.idSale = idSale;
		this.productQuantity = productQuantity;
		this.saleValue = saleValue;
		this.ivaValue = ivaValue;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	


	public String getIdSale() {
		return idSale;
	}
	public void setIdSale(String idSale) {
		this.idSale = idSale;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public float getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(float saleValue) {
		this.saleValue = saleValue;
	}
	public float getIvaValue() {
		return ivaValue;
	}
	public void setIvaValue(float ivaValue) {
		this.ivaValue = ivaValue;
	}
    
	
	
	
	

}
