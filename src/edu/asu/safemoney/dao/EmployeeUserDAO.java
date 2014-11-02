package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;

public interface EmployeeUserDAO {

	public List<RequestDTO> displayEmployeeUserAccountDAO(int memberId);
	public long returnCustomerAccountNo(int memberId);
	public List<RequestDTO> displayEmployeeUserTransactionDAO(int memberId);
	public List<TransactionDTO> getTransactionListForCustomer(int memberId);
}
