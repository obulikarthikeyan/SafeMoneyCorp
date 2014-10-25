package edu.asu.safemoney.service.impln;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.ManageExternalUserAccountService;


@Service
public class ManageExternalUserAccountServiceImpl implements ManageExternalUserAccountService{
	
	@Autowired ManageExternalUserAccountDAO manageExternalUserAccountDAO; 
		
	@Override
	@Transactional
	public void updateUser(UserModel userModel) {
		manageExternalUserAccountDAO.updateUser(userModel);
	}

	@Override
	@Transactional
	public void deleteUser(String nameOfUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public UserDTO displayUserAccount(int memberId){
		UserDTO userDTO = manageExternalUserAccountDAO.displayUserAccountDAO(memberId);
		return userDTO;
	}
	
	@Transactional
	public AccountModel getAccountDetails(int memberId) {
		// TODO Auto-generated method stub
		AccountModel accountModel = manageExternalUserAccountDAO.getAccountDetails(memberId);
		
		return accountModel;
	}

}
