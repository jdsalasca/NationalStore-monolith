package com.shop.models;

public class SalesByClient {
	
	String client_Document;
	String client_name;
	double client_totalSpent;
	public SalesByClient(String client_Document, String client_name, double client_totalSpent) {
		super();
		this.client_Document = client_Document;
		this.client_name = client_name;
		this.client_totalSpent = client_totalSpent;
	}
	public SalesByClient() {
		super();
	}
	public String getClient_Document() {
		return client_Document;
	}
	public void setClient_Document(String client_Document) {
		this.client_Document = client_Document;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public double getClient_totalSpent() {
		return client_totalSpent;
	}
	public void setClient_totalSpent(double client_totalSpent) {
		this.client_totalSpent = client_totalSpent;
	}
	
	

}
