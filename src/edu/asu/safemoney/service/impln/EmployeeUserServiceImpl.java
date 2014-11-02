package edu.asu.safemoney.service.impln;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.safemoney.service.AdminUserService;
import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dao.*;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;

@Service
public class EmployeeUserServiceImpl implements EmployeeUserService{
	
	@Autowired
	private EmployeeUserDAO employeeUserDAO;
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private ManageExternalUserAccountDAO manageExternalUserAccountDAO;
	
	
	@Autowired
	private AdminUserService adminUserService;
	
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
		
		if(custUserDTO.getUserTypeId().getUserTypeId()!=322 && custUserDTO.getUserTypeId().getUserTypeId()!=366)
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
		requestDTO.setRequestId(ExternalUserHelper.generateRandomNumber());
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
	public boolean getViewRequestList(int requestId)
	{
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

	@Transactional
	@Override
	public List<PaymentRequestDTO> getPaymentRequest() {
		// TODO Auto-generated method stub
		List<PaymentRequestDTO> requestList = employeeUserDAO.getPaymentRequest();
		/*for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}*/
		return requestList;
	}

	@Transactional
	@Override
	public List<TransactionDTO> getTransactionRequest() {
		// TODO Auto-generated method stub
		List<TransactionDTO> requestList = employeeUserDAO.getTransactionRequest();
		/*for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}*/
		return requestList;
	}

	@Transactional
	@Override
	public boolean updatePaymentRequest(long paymentRequestId, String status) {
		// TODO Auto-generated method stub
		PaymentRequestDTO paymentDTO = manageExternalUserAccountDAO
				.getPaymentRequestByPaymentId(paymentRequestId);
		paymentDTO.setStatus(status);
		if (manageExternalUserAccountDAO.updatePaymentRequest(paymentDTO))
			return true;
		else
			return false;
	}
	
	
	@Transactional
	@Override
	public boolean updateTransactionRequest(long transactionRequestId,String status) {
		// TODO Auto-generated method stub
		TransactionDTO transactionDTO = manageExternalUserAccountDAO.getTransactionByTransactionId(transactionRequestId);
		transactionDTO.setStatus(status);
		transactionDTO.setProcessedDate(new Date());
		if (manageExternalUserAccountDAO.updateTransactionRequest(transactionDTO))
			return true;
		else
			return false;
	}
	
	@Transactional
	@Override
	public boolean makeCredit(int memberID, double amount) {
		// TODO Auto-generated method stub
		
		return employeeUserDAO.makeCredit(memberID, amount);
	}

	@Transactional
	@Override
	public boolean makeDebit(int memberID, double amount) {
		// TODO Auto-generated method stub
		
		
		return employeeUserDAO.makeDebit(memberID, amount);
	}

	@Override
	@Transactional
	public PaymentRequestDTO getPaymentDTOById(long paymentRequestId) {
		// TODO Auto-generated method stub
		PaymentRequestDTO paymentRequestDTO = employeeUserDAO.getPaymentDTOById(paymentRequestId);
		
		return paymentRequestDTO;
	}

	@Override
	@Transactional
	public TransactionDTO getTransactionDTOById(long transactionRequestId) {
		// TODO Auto-generated method stub
		TransactionDTO transactionDTO = employeeUserDAO.getTransactionDTOById(transactionRequestId);
		return transactionDTO;
	}

	@Override
	@Transactional
	public int getMemberIdByAccount(long accountNumber) {
		// TODO Auto-generated method stub
		int foundAccountNumber = employeeUserDAO.getMemberIdByAccount(accountNumber);
		return foundAccountNumber;
	
		
	}

	
}
