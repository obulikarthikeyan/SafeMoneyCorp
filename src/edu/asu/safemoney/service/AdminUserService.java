package edu.asu.safemoney.service;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;

public interface AdminUserService {
	
	public List<RequestDTO> getExterUserAccountRequests();
	
	public boolean generateBankAccount(int memberId);
	
	public boolean approveExtUserRequest(long requestId);
	
	public boolean declineExtUserRequest(long requestId);
	
	public boolean deleteExtUserAccount(int memberId);

}
