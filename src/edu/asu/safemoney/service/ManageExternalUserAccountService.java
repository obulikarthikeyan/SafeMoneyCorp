package edu.asu.safemoney.service;

import org.springframework.beans.factory.annotation.Autowired;

import antlr.collections.List;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.*;

public interface ManageExternalUserAccountService {

	public boolean updateUser(ModifyUserModel modifyUserModel);
	// Can pass User Name in deleteUser ***
	public void deleteUser(String userName);
	public UserDTO displayUserAccount(int memberId);
	public AccountModel getAccountDetails(int memberId);
	public double getAccountBalance(int memberId);
	public boolean makeCreditTransaction(int memberID, double amount);
	public String makeDebitTransaction(int memberID, double amount);
		// edu.asu.safemoney.service.impln.List<UserDTO> displayUserAccount(
	//		String nameOfUser);
}
