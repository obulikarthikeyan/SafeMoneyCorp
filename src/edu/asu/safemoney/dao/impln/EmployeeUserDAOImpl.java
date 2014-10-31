package edu.asu.safemoney.dao.impln;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.EmployeeUserDAO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;

@Repository
public class EmployeeUserDAOImpl implements EmployeeUserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<RequestDTO> displayEmployeeUserAccountDAO(int memberId){
		// query for user details using userName and save them in a list. 
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM RequestDTO r WHERE r.memberId = :memberId");
		query.setInteger("memberId", memberId);
		List<RequestDTO> requestList = (List<RequestDTO>)query.list();
		return requestList;
	}
}
