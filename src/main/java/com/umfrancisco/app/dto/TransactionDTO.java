package com.umfrancisco.app.dto;

import java.time.LocalDateTime;
import com.umfrancisco.app.model.enums.TransactionStatus;
import com.umfrancisco.app.model.enums.TransactionType;

public class TransactionDTO {
	
	private Long transactionId;
	private Long sourceAccountId;
	private Long destinationAccountId;
	private TransactionType type;
	private TransactionStatus status;
	private LocalDateTime createAt;
	
	public TransactionDTO() {
		
	}
	
	public TransactionDTO(Long transactionId, Long sourceAccountId, Long destinationAccountId, TransactionType type,
			TransactionStatus status) {
		this.transactionId = transactionId;
		this.sourceAccountId = sourceAccountId;
		this.destinationAccountId = destinationAccountId;
		this.type = type;
		this.status = status;
		this.createAt = LocalDateTime.now();
	}
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getSourceAccountId() {
		return sourceAccountId;
	}
	public void setSourceAccountId(Long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}
	public Long getDestinationAccountId() {
		return destinationAccountId;
	}
	public void setDestinationAccountId(Long destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
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
		return "TransactionDTO [transactionId=" + transactionId + ", sourceAccountId=" + sourceAccountId
				+ ", destinationAccountId=" + destinationAccountId + ", type=" + type + ", status=" + status
				+ ", createAt=" + createAt + "]";
	}
	
}
