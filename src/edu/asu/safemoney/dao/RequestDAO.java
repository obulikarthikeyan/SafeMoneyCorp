package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;

public interface RequestDAO {
	
	public boolean generateRequest(RequestDTO requestDTO);
	
	public List<RequestDTO> getRequestsForAdminUser();

}
