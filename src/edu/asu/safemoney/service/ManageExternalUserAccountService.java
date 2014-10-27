package edu.asu.safemoney.service;

import org.springframework.beans.factory.annotation.Autowired;

import antlr.collections.List;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.UserModel;


public interface ManageExternalUserAccountService {

	public void updateUser(UserModel userModel);
	// Can pass User Name in deleteUser ***
	public void deleteUser(String userName);
	public UserDTO displayUserAccount(int memberId);
	public AccountModel getAccountDetails(int memberId);
	// edu.asu.safemoney.service.impln.List<UserDTO> displayUserAccount(
	//		String nameOfUser);
}
