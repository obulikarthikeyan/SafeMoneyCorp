package edu.asu.safemoney.service;

import org.springframework.security.core.userdetails.User;

import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.model.UserModel;

public interface LoginService {
	
	public String getSiteKeyForUserName(String userName);
	
	public void createUser(UserModel user);
	
	public int getMemberId(String userName);

}
