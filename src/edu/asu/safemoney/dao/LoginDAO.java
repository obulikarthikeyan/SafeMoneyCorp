package edu.asu.safemoney.dao;

import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.UserDTO;

public interface LoginDAO {

	public String getSiteKey(String userName);
	
	public UserDTO getUserByMemberId(int memberId);
	
	public int getMemberIdByUserName(String userName);
	
	public LoginDTO getLoginDetails(String userName);
}
