package com.shop.models;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sale")
public class Sale {
	
	@Id
	private String id;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String clientDocument;
    
    private String sale_code;
        
	private List<SaleDetails> saleDetails;
	private float sale_iva;
    
	private float total_venta;
    
	private float valor_venta;
	
	
	
	

	
	
	public Sale(@Size(min = 3, max = 200, message = "Default") String clientDocument, String sale_code,
			List<SaleDetails> saleDetails, float sale_iva, float total_venta, float valor_venta) {
		super();
		this.clientDocument = clientDocument;
		this.sale_code = sale_code;
		this.saleDetails = saleDetails;
		this.sale_iva = sale_iva;
		this.total_venta = total_venta;
		this.valor_venta = valor_venta;
	}
	public Sale(String id, @Size(min = 3, max = 200, message = "Default") String clientDocument, String sale_code,
			List<SaleDetails> saleDetails, float sale_iva, float total_venta, float valor_venta) {
		super();
		this.id = id;
		this.clientDocument = clientDocument;
		this.sale_code = sale_code;
		this.saleDetails = saleDetails;
		this.sale_iva = sale_iva;
		this.total_venta = total_venta;
		this.valor_venta = valor_venta;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSale_code() {
		return sale_code;
	}
	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}
	public float getSale_iva() {
		return sale_iva;
	}
	public void setSale_iva(float sale_iva) {
		this.sale_iva = sale_iva;
	}
	public Sale() {
		super();
	}
	public Sale(String id) {
		super();
		this.id = id;
	}



	public String getClientDocument() {
		return clientDocument;
	}
	public void setClientDocument(String clientDocument) {
		this.clientDocument = clientDocument;
	}
	public float getTotal_venta() {
		return total_venta;
	}
	public void setTotal_venta(float total_venta) {
		this.total_venta = total_venta;
	}
	public float getValor_venta() {
		return valor_venta;
	}
	public void setValor_venta(float valor_venta) {
		this.valor_venta = valor_venta;
	}
	public List<SaleDetails> getSaleDetails() {
		return saleDetails;
	}
	public void setSaleDetails(List<SaleDetails> saleDetails) {
		this.saleDetails = saleDetails;
	}
	
	

    
    
    
    
	
	
    
    
	
	
	
	
	

}
