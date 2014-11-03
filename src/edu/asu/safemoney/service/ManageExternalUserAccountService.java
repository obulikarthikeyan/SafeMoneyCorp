package edu.asu.safemoney.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

//import antlr.collections.List;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.*;


public interface ManageExternalUserAccountService {

	public boolean updateUser(ModifyUserModel modifyUserModel);
	// Can pass User Name in deleteUser ***
	public boolean deleteUser(int memberId);
	public UserDTO displayUserAccount(int memberId);
	public AccountModel getAccountDetails(int memberId);
	public double getAccountBalance(int memberId);
	public String makeCreditTransaction(int memberID, double amount, int fromMemberId, String type);
	public String makeDebitTransaction(int memberID, double amount, int toMemberId, String type);
	public String makeTransform(int memberID, double amount, long toAccount);
	public List<PaymentRequestDTO> getPaymentRequest(int memberId);
	public String authorizePayment (long paymentId );
	public String declinePayment(long paymentId);
	public String submitPayment(long paymentId);
	public String initiatePayment (int fromMemberId, long toAccount,  double amount,String description);
	public List<TransactionDTO> getApprovedTransactionListForUser(int memberId);

	public boolean findAccount(long accountNumber);

	public List<TransactionDTO> getTransactionListForCustomer(int memberId);
	//public TransactionDTO displayTransactionList(int memberId);

	
	public boolean sendTransactionModificationRequest(TransactionModel transactionModel, int memberId);
	
	public boolean deleteTransaction(long transactionId, int memberId);
	
	public boolean sendTransactionDeletionRequest(long transactionId, int memberId);
	
	public boolean writeCertFile(MultipartFile file, String filePath);


	public boolean createRequest(TransactionModel transactionModel, int memberId);
}
