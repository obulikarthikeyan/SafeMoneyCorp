package edu.asu.safemoney.service;

import edu.asu.safemoney.model.UserModel;

public interface ManageExternalUserAccountService {

	public void updateUser(UserModel userModel);
	// Can pass User Name in deleteUser ***
	public void deleteUser(String userName);
	public List<UserDTO> displayUserAccount(UserModel userModel);
}
