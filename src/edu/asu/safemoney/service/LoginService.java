package edu.asu.safemoney.service;

import edu.asu.safemoney.model.UserModel;

public interface LoginService {
	
	public String getSiteKeyForUserName(String userName);
	
	public void createUser(UserModel user);

}
