package edu.asu.safemoney.service.impln;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dto.UserDTO;
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

	@Override
	public UserDTO displayUserAccount(int memberId){
		UserDTO userDTO = manageExternalUserAccountDAO.displayUserAccountDAO(memberId);
		return userDTO;
	}

}
