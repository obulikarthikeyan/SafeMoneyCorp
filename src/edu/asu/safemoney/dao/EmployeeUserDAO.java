package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;

public interface EmployeeUserDAO {
	public List<PaymentRequestDTO> getPaymentRequest();

	public List<TransactionDTO> getTransactionRequest();
	public PaymentRequestDTO getPaymentDTOById(long paymentRequestId);
	public TransactionDTO getTransactionDTOById(long transactionId);
	public boolean makeDebit(int memberID, double amount);
	public boolean makeCredit(int memberID, double amount);
	public int getMemberIdByAccount(long accountNumber);
	
}
