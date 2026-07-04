package com.umfrancisco.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.umfrancisco.app.model.enums.AccountStatus;
import com.umfrancisco.app.model.enums.AccountType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long accountId;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="customer_id")
	private Customer customer;
	private BigDecimal balance;
	private AccountType type;
	private AccountStatus status;
	private LocalDateTime createdAt;
	
	public Account() {
		
	}
	
	public Account(Long accountId, Customer customer, BigDecimal balance, AccountType type) {
		this.accountId = accountId;
		this.customer = customer;
		this.balance = balance;
		this.type = type;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customer=" + customer + ", balance=" + balance + ", type=" + type
				+ ", status=" + status + ", createdAt=" + createdAt + "]";
	}
	
}
