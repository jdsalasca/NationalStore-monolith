package com.shop.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saledetails")
public class SaleDetails {
	
	@Id
	private String id;
	private String productCode;//
    
	private String idSale;

	private int productQuantity;//

	private float total_value;
	private float venta_value;

	private float ivaValue;
	
	

	public SaleDetails(String id) {
		super();
		this.id = id;
	}
	
	
	public SaleDetails() {
		super();
	}
	
	public SaleDetails(String productCode, String idSale, int productQuantity, float total_value, float venta_value,
			float ivaValue) {
		super();
		this.productCode = productCode;
		this.idSale = idSale;
		this.productQuantity = productQuantity;
		this.total_value = total_value;
		this.venta_value = venta_value;
		this.ivaValue = ivaValue;
	}


	public SaleDetails(String id, String productCode, String idSale, int productQuantity, float total_value,
			float venta_value, float ivaValue) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.idSale = idSale;
		this.productQuantity = productQuantity;
		this.total_value = total_value;
		this.venta_value = venta_value;
		this.ivaValue = ivaValue;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
		public float getTotal_value() {
		return total_value;
	}


	public void setTotal_value(float total_value) {
		this.total_value = total_value;
	}


	public float getVenta_value() {
		return venta_value;
	}


	public void setVenta_value(float venta_value) {
		this.venta_value = venta_value;
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

	public float getIvaValue() {
		return ivaValue;
	}
	public void setIvaValue(float ivaValue) {
		this.ivaValue = ivaValue;
	}
    

}
