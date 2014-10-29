package edu.asu.safemoney.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;




//import antlr.collections.List;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.*;


public interface ManageExternalUserAccountService {

	public boolean updateUser(ModifyUserModel modifyUserModel);
	// Can pass User Name in deleteUser ***
	public void deleteUser(String userName);
	public UserDTO displayUserAccount(int memberId);
	public AccountModel getAccountDetails(int memberId);
	public double getAccountBalance(int memberId);
	public String makeCreditTransaction(int memberID, double amount, int fromMemberId, String type);
	public String makeDebitTransaction(int memberID, double amount, int toMemberId, String type);
	public String makeTransform(int memberID, double amount, long toAccount);
	public List<PaymentRequestDTO> getPaymentRequest(int memberId);
	public String authorizePayment (long paymentId ); 
		// edu.asu.safemoney.service.impln.List<UserDTO> displayUserAccount(
	//		String nameOfUser);
}
