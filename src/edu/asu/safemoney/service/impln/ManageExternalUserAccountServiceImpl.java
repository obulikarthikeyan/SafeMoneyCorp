package edu.asu.safemoney.service.impln;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;







import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

@Service
public class ManageExternalUserAccountServiceImpl implements
		ManageExternalUserAccountService {

	@Autowired
	ManageExternalUserAccountDAO manageExternalUserAccountDAO;
	
	
	@Autowired
	RequestDAO requestDAO;
	

	/*private AccountModel accntModel;

	public AccountModel getAccntModel() {
		return accntModel;
	}

	public void setAccntModel(AccountModel accntModel) {
		this.accntModel = accntModel;
	}*/

	@Override
	@Transactional
	public boolean updateUser(ModifyUserModel modifyUserModel) {
		boolean isUpdated = manageExternalUserAccountDAO.updateUser(modifyUserModel);
		if(isUpdated)
		{
			return true;
		}
		return false;
	}
	
	
	
	@Override
	@Transactional
	public boolean deleteUser(int memberId) {		
		
			UserDTO userDTO= displayUserAccount(memberId);
			List<RequestDTO> requestList= userDTO.getRequestDTOList();
			RequestDTO requestDTO = new RequestDTO();
			requestDTO.setAuthorityUserTypeId(123);
			requestDTO.setMemberId(userDTO);
			requestDTO.setRequestType("DELETE_ACCOUNT");
			requestDTO.setStatus("NEW");
			requestDTO.setRequestDate(new Date());
			requestDTO.setProcessedDate(null);
			requestDTO.setAuthorizingMemberId(null);
			requestDTO.setAuthorizingAuthority("INT_BANK_ADM");
			
			if(requestList!=null){
				
				for(RequestDTO req : requestList)
				{
					System.out.println("req type: " + req.getRequestType());
					if(req.getRequestType().equals("DELETE_ACCOUNT"))
					{
						return false;
					}
				}
				requestList.add(requestDTO);
			}
			
			else{
				requestList= new ArrayList<RequestDTO>();
				requestList.add(requestDTO);
			}
			
			userDTO.setRequestDTOList(requestList);
			
			boolean isDeleted= requestDAO.createRequest(userDTO);
			if(isDeleted){
				return true;
			}
			return false;
	}

	@Override
	@Transactional
	public UserDTO displayUserAccount(int memberId) {
		UserDTO userDTO = manageExternalUserAccountDAO
				.displayUserAccountDAO(memberId);
		return userDTO;
	}

	@Transactional
	public AccountModel getAccountDetails(int memberId) {
		// TODO Auto-generated method stub
		AccountModel accountModel = manageExternalUserAccountDAO
				.getAccountDetails(memberId);
		//this.setAccntModel(accountModel);
		return accountModel;
	}

	@Transactional
	public double getAccountBalance(int memberId) {
		AccountModel accountModel = manageExternalUserAccountDAO
				.getAccountDetails(memberId);
		if (accountModel != null) {
			return accountModel.getAmount();
		} else {
			return -1;
		}
	}

	@Transactional
	@Override
	public String makeDebitTransaction(int memberID, double amount, int toMemberId) {
		// TODO Auto-generated method stub
		if (amount > 2000) {
			
			AccountModel accountModel =getAccountDetails(memberID);
			AccountModel toaccountModel = getAccountDetails(toMemberId);
			TransactionDTO txnDTO = new TransactionDTO();
			txnDTO.setAmount(amount);
			txnDTO.setDate(new Date());
			txnDTO.setFromAccount(accountModel.getAccountNo());
			txnDTO.setToAccount(toaccountModel.getAccountNo());
			txnDTO.setIsAuthorized(false);// boolean
			txnDTO.setIsCritical(true);
			txnDTO.setMemberId(displayUserAccount(memberID));// input
																// UserDTO
			txnDTO.setStatus("PENDING");//
			txnDTO.setTransactionId(ExternalUserHelper.generateRandomNumber());// long
			txnDTO.setTransactionType("Debit");

			boolean isTxnCreated = manageExternalUserAccountDAO
					.createTransaction(txnDTO);
			if (isTxnCreated) {
				return "CriticalDebit";
			}
			
		} else {
			double balance = getAccountBalance(memberID);
			if (balance > amount) {
				balance -= amount;
				boolean isSuccess = manageExternalUserAccountDAO
						.updateAccountBalance(memberID, balance);
				if (isSuccess) {
					AccountModel accountModel = getAccountDetails(memberID);
					AccountModel toaccountModel = getAccountDetails(toMemberId);
					TransactionDTO txnDTO = new TransactionDTO();
					txnDTO.setAmount(amount);
					txnDTO.setDate(new Date());
					txnDTO.setFromAccount(accountModel.getAccountNo());
					txnDTO.setToAccount(toaccountModel.getAccountNo());
					txnDTO.setIsAuthorized(true);// boolean
					txnDTO.setIsCritical(false);
					txnDTO.setMemberId(displayUserAccount(memberID));// input
																		// UserDTO
					txnDTO.setStatus("APPROVED");//
					txnDTO.setTransactionId(ExternalUserHelper.generateRandomNumber());// long
					txnDTO.setTransactionType("Debit");

					boolean isTxnCreated = manageExternalUserAccountDAO
							.createTransaction(txnDTO);
					if (isTxnCreated) {
						return "success";
					}
				} else {
					return "failure";
				}
			}
			else
			{
				return "NOFUND";
			}
		}
		return "failure";
	}
	
	@Transactional
	@Override
	public String makeCreditTransaction(int memberID, double amount,int fromMemberId) {
		// TODO Auto-generated method stub
		if (amount > 2000) {
			
			AccountModel accountModel = getAccountDetails(memberID);
			AccountModel fromAccountModel = getAccountDetails(fromMemberId);
			TransactionDTO txnDTO = new TransactionDTO();
			txnDTO.setAmount(amount);
			txnDTO.setDate(new Date());
			txnDTO.setFromAccount(fromAccountModel.getAccountNo());
			txnDTO.setToAccount(accountModel.getAccountNo());
			txnDTO.setIsAuthorized(false);// boolean
			txnDTO.setIsCritical(true);
			txnDTO.setMemberId(displayUserAccount(memberID));// input
																// UserDTO
			txnDTO.setStatus("PENDING");//
			txnDTO.setTransactionId(ExternalUserHelper.generateRandomNumber());// long
			txnDTO.setTransactionType("Credit");

			boolean isTxnCreated = manageExternalUserAccountDAO.createTransaction(txnDTO);
			if (isTxnCreated) {
				return "CriticalCredit";
				
				
			}
		} else {
			double balance = getAccountBalance(memberID);
			if (amount>=0) {
				balance += amount;
				boolean isSuccess = manageExternalUserAccountDAO.updateAccountBalance(memberID, balance);
				if (isSuccess) {
					AccountModel accountModel =getAccountDetails(memberID);
					AccountModel fromAccountModel = getAccountDetails(fromMemberId);
					TransactionDTO txnDTO = new TransactionDTO();
					txnDTO.setAmount(amount);
					txnDTO.setDate(new Date());
					txnDTO.setFromAccount(fromAccountModel.getAccountNo());
					txnDTO.setToAccount(accountModel.getAccountNo());
					txnDTO.setIsAuthorized(true);// boolean
					txnDTO.setIsCritical(false);
					txnDTO.setMemberId(displayUserAccount(memberID));// input
																		// UserDTO
					txnDTO.setStatus("APPROVED");//
					txnDTO.setTransactionId(ExternalUserHelper.generateRandomNumber());// long
					txnDTO.setTransactionType("Credit");

					boolean isTxnCreated = manageExternalUserAccountDAO.createTransaction(txnDTO);
					if (isTxnCreated) {
						return "success";
					}
				} else {
					return "failure";
				}
			}
			else
			{
				return "CreditMountNegative";
			}
		}
		return "failure";

	}
	
	@Transactional
	@Override
	public String makeTransform(int memberID, double amount, long toAccount)
	{
		
		int toMemberId = manageExternalUserAccountDAO.getMemberIdByAccount(toAccount);																																
		String debitResult = this.makeDebitTransaction(memberID, amount, toMemberId);
		if(debitResult.equals("success"))
		{
			String creditResult = this.makeCreditTransaction(toMemberId, amount, memberID);
			if(creditResult.equals("success"))
			{
				return "success";
			}
			else
			{
				return creditResult+":Cannot Credit to the account because";
			}
		}
		else
		{
			return debitResult+"Cannot Debit from your account because";
		}
		//return "failure";
	}

}
