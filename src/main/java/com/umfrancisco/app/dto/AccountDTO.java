package com.umfrancisco.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.umfrancisco.app.model.enums.AccountStatus;
import com.umfrancisco.app.model.enums.AccountType;

public class AccountDTO {
	
	private Long accountId;
	private String customerEmail;
	private BigDecimal balance;
	private AccountType type;
	private AccountStatus status;
	private LocalDateTime createdAt;
	
	public AccountDTO() {
		
	}
	
	public AccountDTO(Long accountId, String customerEmail, BigDecimal balance, AccountType type) {
		this.accountId = accountId;
		this.customerEmail = customerEmail;
		this.balance = balance;
		this.type = type;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
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
		return "AccountDTO [accountId=" + accountId + ", customerEmail=" + customerEmail + ", balance=" + balance + ", type="
				+ type + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
	
}
