package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;

public interface EmployeeUserDAO {

	public List<RequestDTO> displayEmployeeUserAccountDAO(int memberId);
}
