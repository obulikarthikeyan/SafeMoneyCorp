package edu.asu.safemoney.service;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;

public interface EmployeeUserService {
	
	public boolean sendExtUserViewRequests(int customerId, int employeeId);
	
	public boolean sendExtUserTransactionViewRequests(int customerId, int employeeId);
	
	public boolean authorizeCreditTransaction(int requestId);
	
	public boolean authorizePaymentTransaction(int requestId);
	
	public boolean getViewRequestList(int requestId);
	
	public List<RequestDTO> getRequestList(int memberId);
	
	public List<RequestDTO> getTransactionList(int memberId);
	
	public int getCustomerId (int requestId);
	
	public long getAccountNo(int memberId);
	
	public List<RequestDTO> getTransactionRequestList(int memberId);
	
	public List<TransactionDTO> getAllTransactions(int memberId);

}
