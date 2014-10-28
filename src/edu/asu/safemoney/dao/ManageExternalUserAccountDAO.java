package edu.asu.safemoney.dao;

import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;


public interface ManageExternalUserAccountDAO {

	public boolean updateUser(ModifyUserModel modifyUserModel);
	public UserDTO displayUserAccountDAO(int memberId);
	public AccountModel getAccountDetails(int memberId);
	public boolean updateAccountBalance(int memberId, double amount);
	public boolean createTransaction(TransactionDTO transactionDTO);
	
	public boolean createAccount(AccountDTO accountDTO);
	public int getMemberIdByAccount(long accountNumber);
	public boolean deleteExtUserAccount(int memberId);
	
	
}
