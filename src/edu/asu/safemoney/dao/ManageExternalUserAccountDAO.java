package edu.asu.safemoney.dao;

import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.dto.*;

public interface ManageExternalUserAccountDAO {

	public void updateUser(UserModel userModel);
	
	public AccountModel getAccountDetails(int memberId);

}
