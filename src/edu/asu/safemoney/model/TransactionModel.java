package edu.asu.safemoney.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionModel {
	
	private long transactionId;
	private long fromAccount;
	private long toAccound;
	
	private Date transactionDate;
	private double transactionAmount;
	private String transactionStatus;
	private String transactionType;
	private boolean isCritical;
	private boolean isAuthorized;
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public long getToAccound() {
		return toAccound;
	}
	public void setToAccound(long toAccound) {
		this.toAccound = toAccound;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public boolean isCritical() {
		return isCritical;
	}
	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}
	public boolean isAuthorized() {
		return isAuthorized;
	}
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	
	
	
	
	
	
}
