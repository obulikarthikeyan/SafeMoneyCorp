package edu.asu.safemoney.service;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;

public interface EmployeeUserService {
	
	public boolean sendExtUserViewRequests(int memberId);
	
	public boolean authorizeCreditTransaction(int requestId);
	
	public boolean authorizePaymentTransaction(int requsetId);

}
