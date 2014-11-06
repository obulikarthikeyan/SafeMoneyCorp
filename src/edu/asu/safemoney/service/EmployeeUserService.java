package edu.asu.safemoney.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;

public interface EmployeeUserService {
	
	public boolean sendExtUserViewRequests(int customerId, int employeeId);
	
	public boolean sendExtUserTransactionViewRequests(int customerId, int employeeId);
	
	public boolean authorizeCreditTransaction(int requestId);
	
	public boolean authorizePaymentTransaction(int requestId);
	
	public boolean getViewRequestList(int requestId);
	

	public List<PaymentRequestDTO> getPaymentRequest();
	public List<TransactionDTO> getTransactionRequest();

	
	public boolean updatePaymentRequest(long paymentRequestId,String status);
	public boolean updateTransactionRequest(long transactionRequestId, String status);
	public boolean makeCredit(int memberID, double amount);
	public boolean makeDebit(int memberID, double amount);
	public PaymentRequestDTO getPaymentDTOById(long paymentRequestId);
	public TransactionDTO getTransactionDTOById(long transactionRequestId);
	
	public int getMemberIdByAccount(long accountNumber);

	public List<RequestDTO> getRequestList(int memberId);
	
	public List<RequestDTO> getTransactionList(int memberId);
	
	public int getCustomerId (int requestId);
	
	public long getAccountNo(int memberId);
	
	public List<RequestDTO> getTransactionRequestList(int memberId);
	
	public List<TransactionDTO> getAllTransactions(int memberId);
	
	public List<TransactionReviewDTO> getTransactionReviewList();
	
	public boolean approveTransactionReview(long transactionReviewId, int memberId);
	
	public boolean declineTransactionReview(long transactionReviewId, int memberId);

}
