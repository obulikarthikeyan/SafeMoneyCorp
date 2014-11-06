package edu.asu.safemoney.service.impln;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.AdminUserDAO;
import edu.asu.safemoney.dao.EmployeeUserDAO;
import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDAO adminUserDAO;
	
	@Autowired LoginDAO loginDAO;
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private ManageExternalUserAccountDAO extUserAccountDAO;
	
	@Autowired
	private EmployeeUserDAO employeeUserDAO;
	
	@Transactional
	@Override
	public ModifyUserModel getEmployee(int memberId){
		ModifyUserModel modifyUserModel= employeeUserDAO.getEmployeeDetails(memberId); 
		
		return modifyUserModel;
	}
	
	@Transactional
	@Override
	public List<RequestDTO> getExterUserAccountRequests() {
		// TODO Auto-generated method stub
		
		List<RequestDTO> requestList = requestDAO.getRequestsForAdminUser();
		for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}
		return requestList;
	}

	@Transactional
	@Override
	public boolean generateBankAccount(int memberId) {
		// TODO Auto-generated method stub
		UserDTO userDTO = extUserAccountDAO.displayUserAccountDAO(memberId);
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNo(ExternalUserHelper.generateAccountNumber(memberId));
		accountDTO.setAmount(0.00);
		accountDTO.setIsActive("true");
		accountDTO.setMemberId(userDTO);
		boolean isAccountCreated = extUserAccountDAO.createAccount(accountDTO);
		if(isAccountCreated)
		{
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean deleteExtUserAccount(int memberId)
	{
		boolean isUserDeleted = extUserAccountDAO.deleteExtUserAccount(memberId);
		if(isUserDeleted)
		{
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public boolean approveExtUserRequest(long requestId) {
		// TODO Auto-generated method stub
		RequestDTO requestDTO = requestDAO.getRequestByRequestId(requestId);
		if(requestDTO != null && requestDTO.getStatus().equals("NEW"))
		{
			if(requestDTO.getRequestType().equals("CREATE_ACCOUNT"))
			{
				boolean isAccountGenerated = generateBankAccount(requestDTO.getMemberId().getMemberId());
				if(isAccountGenerated)
				{
					requestDTO.setStatus("APPROVED");
					requestDTO.setProcessedDate(new Date());
					boolean isRequestUpdated = requestDAO.updateRequest(requestDTO);
					if(isRequestUpdated)
					{
						return true;
					}
				}
			}
			else if(requestDTO.getRequestType().equals("DELETE_ACCOUNT"))
			{
				boolean isDeleted = deleteExtUserAccount(requestDTO.getMemberId().getMemberId());
				if(isDeleted)
				{
					return true;
				}
			}
			
		}
		return false;
	}

	@Transactional
	@Override
	public boolean declineExtUserRequest(long requestId) {
		// TODO Auto-generated method stub
		RequestDTO requestDTO = requestDAO.getRequestByRequestId(requestId);
		if(requestDTO != null && requestDTO.getStatus().equals("NEW"))
		{
			if(requestDTO.getRequestType().equals("CREATE_ACCOUNT") || requestDTO.getRequestType().equals("DELETE_ACCOUNT"))
			{
					requestDTO.setStatus("DECLINED");
					requestDTO.setProcessedDate(new Date());
					boolean isRequestUpdated = requestDAO.updateRequest(requestDTO);
					if(isRequestUpdated)
					{
						return true;
					}
			}
			
		}
		return false;
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
	
	private Date calcuateExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 365);
		Date expiryDate = cal.getTime();
		return expiryDate;
	}
	
	@Transactional
	@Override
	public boolean createEmployee(UserModel userModel){
		System.out.println("entered service");
		if(validateUser(userModel)){
			System.out.println("returned false at service");
			return false;
		}
		else{
		userModel.setEmployee(true);
		userModel.setCreatedDate(new Date());
		userModel.setExpiryDate(calcuateExpiryDate());
		userModel.setIsActive("true");
		userModel.setCreatedBy("ADMIN");
		userModel.setCreatedDate(new Date());
		userModel.setIsCustomer("false");
		userModel.setUserType("INT_BANK_EMP");
		userModel.setUserTypeId(123);
		boolean created= loginDAO.createEmployee(userModel);
		System.out.println("returned true at service");
		return created;
		}
	}
	
	@Transactional
	public boolean validateUser(UserModel userModel){
		String siteKey = loginDAO.getSiteKey(userModel.getUserName());
		boolean emailExist= loginDAO.isEmailExists(userModel.getEmailId());
		if((siteKey != null && !siteKey.isEmpty() )|| emailExist)
		{

			return true;
		}
		else 
			return false;

	}

	
}
