package com.umfrancisco.app.model;

import java.math.BigDecimal;
import com.umfrancisco.app.model.enums.AccountStatus;
import com.umfrancisco.app.model.enums.AccountType;

public class Account {
	
	private Long accountId;
	private Customer customer;
	private BigDecimal balance;
	private AccountType type;
	private AccountStatus status;
	
	public Account() {
		
	}
	
	public Account(Long accountId, Customer customer, BigDecimal balance, AccountType type, AccountStatus status) {
		this.accountId = accountId;
		this.customer = customer;
		this.balance = balance;
		this.type = type;
		this.status = status;
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
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customer=" + customer + ", balance=" + balance + ", type=" + type
				+ ", status=" + status + "]";
	}
	
}
