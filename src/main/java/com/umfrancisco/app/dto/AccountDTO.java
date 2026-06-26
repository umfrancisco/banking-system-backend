package com.umfrancisco.app.dto;

import java.math.BigDecimal;
import com.umfrancisco.app.model.enums.AccountStatus;
import com.umfrancisco.app.model.enums.AccountType;

public class AccountDTO {
	
	private Long accountId;
	private Long customerId;
	private BigDecimal balance;
	private AccountType type;
	private AccountStatus status;
	
	public AccountDTO() {
		
	}
	
	public AccountDTO(Long accountId, Long customerId, BigDecimal balance, AccountType type, AccountStatus status) {
		this.accountId = accountId;
		this.customerId = customerId;
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
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
		return "AccountDTO [accountId=" + accountId + ", customerId=" + customerId + ", balance=" + balance + ", type="
				+ type + ", status=" + status + "]";
	}
	
}
