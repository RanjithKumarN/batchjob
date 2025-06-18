package com.rest.api.BatchProcessor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="CUSTOMER")
public class CustomerDO {

	
	 @Id
	@Column(name = "CUSTOMER_ID")
	 private long id;
	 
	public long getId() {
		return id;
	}
	 public void setId(long id) {
		 this.id = id;
	 }
	@Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
	

	
	
	
	public String getAccountNumber() {	
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
