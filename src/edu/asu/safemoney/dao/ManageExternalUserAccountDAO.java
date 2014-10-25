package edu.asu.safemoney.dao;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.UserModel;


public interface ManageExternalUserAccountDAO {

	public void updateUser(UserModel userModel);
	public UserDTO displayUserAccountDAO(int memberId);

}
