package edu.asu.safemoney.service;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;

public interface AdminUserService {
	
	public List<RequestDTO> getExterUserAccountRequests();

}
