package com.rest.api.BatchProcessor.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name ="BNK_TRANSACTION")
public class TransactionDO {
	
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
	 
	 public Long getTransactionId() {
		return transactionId;
	}
	 public void setTransactionId(Long transactionId) {
		 this.transactionId = transactionId;
	 }
	 @ManyToOne(cascade = CascadeType.PERSIST)
	 @JoinColumn(name = "customer_id")
	 private CustomerDO customer;
	
	public CustomerDO getCustomer() {
		return customer;
	}
	 public void setCustomer(CustomerDO customer) {
		 this.customer = customer;
	 }
	@Column(name = "TRANSACTION_AMOUNT")
    private double trxAmount;
	
	@Column(name = "DESCRIPTION")
    private String description;
	
	@Column(name = "TRANSACTION_DATE")
    private String transactionDate;
	
	@Column(name = "TRANSACTION_TIME")
    private String transactionTime;
	
	
	@Version
	@Column(name = "VERSION")
	private Integer version;
	
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	public double getTrxAmount() {
		return trxAmount;
	}
	public void setTrxAmount(double trxAmount) {
		this.trxAmount = trxAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	


}
