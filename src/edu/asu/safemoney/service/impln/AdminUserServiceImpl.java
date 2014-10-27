package edu.asu.safemoney.service.impln;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.AdminUserDAO;
import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDAO adminUserDAO;
	
	@Autowired
	private RequestDAO requestDAO;

	@Transactional
	@Override
	public List<RequestDTO> getExterUserAccountRequests() {
		// TODO Auto-generated method stub
		
		List<RequestDTO> requestList = requestDAO.getRequestsForAdminUser();
		for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}
		return requestList;
	} 
	

	
}
