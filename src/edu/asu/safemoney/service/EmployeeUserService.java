package edu.asu.safemoney.service;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;

public interface EmployeeUserService {
	
	public boolean sendExtUserViewRequests(int customerId, int employeeId);
	
	public boolean authorizeCreditTransaction(int requestId);
	
	public boolean authorizePaymentTransaction(int requestId);
	
	public boolean getViewRequestList(int requestId);

}
