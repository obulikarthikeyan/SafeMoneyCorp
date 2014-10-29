package edu.asu.safemoney.service.impln;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.asu.safemoney.service.EmployeeUserService;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.*;

@Service
public class EmployeeUserServiceImpl implements EmployeeUserService{
	
	@Autowired
	private EmployeeUserDAO employeeUserDAO;
	
	@Transactional
	@Override
	public boolean sendExtUserViewRequests(int memberId){
		return false;
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