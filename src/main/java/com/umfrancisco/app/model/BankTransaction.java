package com.umfrancisco.app.model;

import java.time.LocalDateTime;
import com.umfrancisco.app.model.enums.TransactionStatus;
import com.umfrancisco.app.model.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class BankTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transactionId;
	@ManyToMany
	private Account sourceAccount;
	@ManyToMany
	private Account destinationAccount;
	private TransactionType type;
	private TransactionStatus status;
	private LocalDateTime createAt;
	
	public BankTransaction() {
		
	}
	
	public BankTransaction(Long transactionId, Account sourceAccount, Account destinationAccount, TransactionType type,
			TransactionStatus status, LocalDateTime createAt) {
		this.transactionId = transactionId;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.type = type;
		this.status = status;
		this.createAt = createAt;
	}
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Account getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public Account getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	@Override
	public String toString() {
		return "BankTransaction [transactionId=" + transactionId + ", sourceAccount=" + sourceAccount
				+ ", destinationAccount=" + destinationAccount + ", type=" + type + ", status=" + status + ", createAt="
				+ createAt + "]";
	}
	
}
