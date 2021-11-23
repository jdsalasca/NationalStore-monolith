package com.shop.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saledetails")
public class SaleDetails {
	
	@Id
	private String id;
	
	@NotBlank
	private Product idProduct;
	@NotBlank
	private Sale idSale;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private int productQuantity;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private float saleValue;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private float ivaValue;
	public SaleDetails(String id, @NotBlank Product idProduct, @NotBlank Sale idSale,
			@Size(min = 3, max = 200, message = "Default") int productQuantity,
			@Size(min = 3, max = 200, message = "Default") float saleValue,
			@Size(min = 3, max = 200, message = "Default") float ivaValue) {
		super();
		this.id = id;
		this.idProduct = idProduct;
		this.idSale = idSale;
		this.productQuantity = productQuantity;
		this.saleValue = saleValue;
		this.ivaValue = ivaValue;
	}
	public SaleDetails() {
		super();
	}
	public SaleDetails(@NotBlank Product idProduct, @NotBlank Sale idSale,
			@Size(min = 3, max = 200, message = "Default") int productQuantity,
			@Size(min = 3, max = 200, message = "Default") float saleValue,
			@Size(min = 3, max = 200, message = "Default") float ivaValue) {
		super();
		this.idProduct = idProduct;
		this.idSale = idSale;
		this.productQuantity = productQuantity;
		this.saleValue = saleValue;
		this.ivaValue = ivaValue;
	}
	public SaleDetails(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Product getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Product idProduct) {
		this.idProduct = idProduct;
	}
	public Sale getIdSale() {
		return idSale;
	}
	public void setIdSale(Sale idSale) {
		this.idSale = idSale;
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
