package edu.asu.safemoney.model;

public class AccountModel {
	
	private long accountNo;
	
	private String firstName;
	
	private String lastName;
	
	private double amount;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
