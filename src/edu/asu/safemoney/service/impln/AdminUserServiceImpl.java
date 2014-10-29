package edu.asu.safemoney.service.impln;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.AdminUserDAO;
import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDAO adminUserDAO;
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private ManageExternalUserAccountDAO extUserAccountDAO;
	
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
	

	
}
