package edu.asu.safemoney.service;

import java.util.List;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.*;
import edu.asu.safemoney.dto.TransactionDTO;;

public interface ManageExternalUserAccountService {

	public boolean updateUser(ModifyUserModel modifyUserModel);
	// Can pass User Name in deleteUser ***
	public boolean deleteUser(int memberId);
	public UserDTO displayUserAccount(int memberId);
	public AccountModel getAccountDetails(int memberId);
	public double getAccountBalance(int memberId);
	public String makeCreditTransaction(int memberID, double amount, int fromMemberId);
	public String makeDebitTransaction(int memberID, double amount, int toMemberId);
	public String makeTransform(int memberID, double amount, long toAccount);
		// edu.asu.safemoney.service.impln.List<UserDTO> displayUserAccount(
	//		String nameOfUser);
	
	public List<TransactionDTO> getApprovedTransactionListForUser(int memberId);
}
