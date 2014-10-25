package edu.asu.safemoney.dao.impln;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.dto.UserDTO;

@Repository
public class ManageExternalUserAccountDAOImpl implements ManageExternalUserAccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private LoginDAOImpl loginDAOImpl;
	
	public void updateUser(UserModel user){
		
		UserDTO userDTO= loginDAOImpl.copyToUserDTO(user);
		Session session= sessionFactory.getCurrentSession();
		session.saveOrUpdate(userDTO);
	}
	
	public List<UserDTO> displayUserAccountDAO(String nameOfUser){
		// query for user details using userName and save them in a list. 
		List userDetails= 
	}
}
