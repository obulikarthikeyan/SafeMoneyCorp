package edu.asu.safemoney.dao;

import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.UserModel;

public interface LoginDAO {

	public String getSiteKey(String userName);
	
	public UserDTO getUserByMemberId(int memberId);
	
	public int getMemberIdByUserName(String userName);
	
	public LoginDTO getLoginDetails(String userName);
	
	public void createUser(UserModel user);
	
	public boolean isEmailExists(String emailId);
}
