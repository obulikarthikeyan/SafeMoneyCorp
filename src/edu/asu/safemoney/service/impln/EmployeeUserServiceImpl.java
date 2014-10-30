package edu.asu.safemoney.service.impln;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.*;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;

@Service
public class EmployeeUserServiceImpl implements EmployeeUserService{
	
	@Autowired
	private EmployeeUserDAO employeeUserDAO;
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	ManageExternalUserAccountDAO manageExternalUserAccountDAO;
	
	@Transactional
	@Override
	public boolean sendExtUserViewRequests(int customerId, int employeeId){
		
		UserDTO empUserDTO = manageExternalUserAccountDAO.displayUserAccountDAO(employeeId);
		UserDTO custUserDTO = manageExternalUserAccountDAO.displayUserAccountDAO(customerId);
		
		if(empUserDTO==null)
		{
			return false;
		}
		
		if(custUserDTO==null)
		{
			return false;
		}
		
		List<RequestDTO> requestList= empUserDTO.getRequestDTOList();
		
		RequestDTO requestDTO = new RequestDTO();
		UserTypeDTO authorityUserTypeId = custUserDTO.getUserTypeId();
		
		requestDTO.setMemberId(empUserDTO);
		requestDTO.setRequestType("VIEW_ACCOUNT");
		requestDTO.setStatus("NEW");
		requestDTO.setRequestDate(new Date());
		requestDTO.setProcessedDate(null);
		requestDTO.setAuthorizingMemberId(customerId);		
		requestDTO.setAuthorityUserTypeId(authorityUserTypeId.getUserTypeId());		
		requestDTO.setAuthorizingAuthority(authorityUserTypeId.getUserType());
		
		if(requestList!=null)
		{
			for (RequestDTO req : requestList)
			{
				if(req.getRequestType().equals("VIEW_ACCOUNT") && req.getAuthorizingMemberId()==customerId)
				{
					return false;
				}
				
			}
			requestList.add(requestDTO);

		}
		else
		{
			requestList= new ArrayList<RequestDTO>();
			requestList.add(requestDTO);
		}
		return true;
	}
	
	@Transactional
	@Override
	public boolean authorizeCreditTransaction(int requestId){
		return false;
	}
	
	@Transactional
	@Override
	public boolean authorizePaymentTransaction(int requsetId){
		return false;
	}
}