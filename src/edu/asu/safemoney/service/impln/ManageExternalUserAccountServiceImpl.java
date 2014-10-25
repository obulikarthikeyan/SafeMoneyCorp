package edu.asu.safemoney.service.impln;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

@Service
public class ManageExternalUserAccountServiceImpl implements ManageExternalUserAccountService{
	
	@Autowired ManageExternalUserAccountDAO manageExternalUserAccountDAO; 
		
	@Override
	public void updateUser(UserModel userModel) {
		manageExternalUserAccountDAO.updateUser(userModel);
	}

	@Override
	public void deleteUser(String nameOfUser) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public AccountModel getAccountDetails(int memberId) {
		// TODO Auto-generated method stub
		AccountModel accountModel = manageExternalUserAccountDAO.getAccountDetails(memberId);
		
		return accountModel;
	}

	/*@Override
	public void displayUserAccount(UserModel userModel) {
		// TODO Auto-generated method stub
		
	}
*/
}
