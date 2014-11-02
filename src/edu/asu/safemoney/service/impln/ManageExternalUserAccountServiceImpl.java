package edu.asu.safemoney.service.impln;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;








//import antlr.collections.List;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
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
			requestDTO.setRequestId(ExternalUserHelper.generateRandomNumber());
			requestDTO.setAuthorityUserTypeId(123);
			requestDTO.setMemberId(userDTO);
			requestDTO.setRequestType("DELETE_ACCOUNT");
			requestDTO.setStatus("NEW");
			requestDTO.setRequestDate(new Date());
			requestDTO.setProcessedDate(null);
			requestDTO.setAuthorizingMemberId(null);
			requestDTO.setAuthorizingAuthority("INT_BANK_ADM");
			
			if(requestList!=null){
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
	public String makeDebitTransaction(int memberID, double amount, int toMemberId, String type) {
		// TODO Auto-generated method stub
		if ((amount > 2000)&&!(type.equals("payment"))) {
			
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
			txnDTO.setTransactionType(type);
			txnDTO.setProcessedDate(null);
			//txn

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
					txnDTO.setTransactionType(type);
					txnDTO.setProcessedDate(new Date());

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
	public String makeCreditTransaction(int memberID, double amount,int fromMemberId,String type) {
		// TODO Auto-generated method stub
		if (true) {
			
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
			txnDTO.setTransactionType(type);
			txnDTO.setProcessedDate(null);
			
			
			boolean isTxnCreated = manageExternalUserAccountDAO.createTransaction(txnDTO);
			if (isTxnCreated) {
				return "Pending Approve by Bank";
				
				
			}
		} 
		return "failure";

	}
	
	@Transactional
	@Override
	public String makeTransform(int memberID, double amount, long toAccount)
	{
		
		int toMemberId = manageExternalUserAccountDAO.getMemberIdByAccount(toAccount);	
		
		String debitResult = this.makeDebitTransaction(memberID, amount, toMemberId,"transfer");
		if(debitResult.equals("success"))
		{
			String creditResult = this.makeCreditTransaction(toMemberId, amount, memberID, "transfer");
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
	
	@Transactional
	@Override
	public List<PaymentRequestDTO> getPaymentRequest(int memberId) {
		// TODO Auto-generated method stub
		List<PaymentRequestDTO> requestList = manageExternalUserAccountDAO.getPaymentRequest(memberId);
		/*for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}*/
		return requestList;
		//return new List<PaymentRequestDTO> aaa;
		//return requestList;
	}

	@Override
	@Transactional
	public String authorizePayment(long paymentId) {
		// TODO Auto-generated method stub
		PaymentRequestDTO paymentDTO =  manageExternalUserAccountDAO.getPaymentRequestByPaymentId(paymentId);
		UserDTO merchantDTO = paymentDTO.getMerchantMemberId();
		
		int merchantMemberId = merchantDTO.getMemberId();
		int customerId = paymentDTO.getAuthorizerMemberId();
		double amount = paymentDTO.getAmount();
		String result = this.makeDebitTransaction(customerId, amount, merchantMemberId,"payment");
		if(result.equals("success"))
		{
			paymentDTO.setStatus("AUTHORIZED");
			if(manageExternalUserAccountDAO.updatePaymentRequest(paymentDTO))
				return "success";
			else
				return result;
		}
		//paymentDTO.setStatus(status);
		return result;
	}
	
	@Override
	@Transactional
	public String declinePayment(long paymentId) {
		// TODO Auto-generated method stub
		PaymentRequestDTO paymentDTO =  manageExternalUserAccountDAO.getPaymentRequestByPaymentId(paymentId);
		paymentDTO.setStatus("DECLINED_CUST");
		manageExternalUserAccountDAO.updatePaymentRequest(paymentDTO);
		
		UserDTO merchantDTO = paymentDTO.getMerchantMemberId();
		int merchantMemberId = merchantDTO.getMemberId();
		int customerId = paymentDTO.getAuthorizerMemberId();
		double amount = paymentDTO.getAmount();
		//	public String makeCreditTransaction(int memberID, double amount,int fromMemberId,String type) 
		AccountModel accountModel = getAccountDetails(customerId);
		AccountModel toaccountModel = getAccountDetails(merchantMemberId);
		TransactionDTO txnDTO = new TransactionDTO();
		txnDTO.setAmount(amount);
		txnDTO.setDate(new Date());
		txnDTO.setFromAccount(accountModel.getAccountNo());
		txnDTO.setToAccount(toaccountModel.getAccountNo());
		txnDTO.setIsAuthorized(false);// boolean
		txnDTO.setIsCritical(true);
		txnDTO.setMemberId(displayUserAccount(customerId));// input
															// UserDTO
		txnDTO.setStatus("DECLINED");//
		txnDTO.setTransactionId(ExternalUserHelper.generateRandomNumber());// long
		txnDTO.setTransactionType("payment");
		txnDTO.setProcessedDate(null);
		// txn

			boolean isTxnCreated = manageExternalUserAccountDAO
					.createTransaction(txnDTO);
			if (isTxnCreated) {
				return "payment declined";
			}
			else
				return "failure";
	
	}

	@Override
	@Transactional
	public String initiatePayment(int fromMemberId, long toAccount, double amount, String description) {
		// TODO Auto-generated method stub
		int toMemberId = manageExternalUserAccountDAO.getMemberIdByAccount(toAccount);
		
		
		
		UserDTO initiater = displayUserAccount(fromMemberId);
		UserTypeDTO initiaterType = initiater.getUserTypeId();
		String initiateStatus;
		if(initiaterType.getUserTypeId()==366)//Merchant
		{
			initiateStatus = "PENDING_AUTH";
			AccountModel fromAccountModel = getAccountDetails(fromMemberId);
			AccountModel toAccountModel = getAccountDetails(toMemberId);
			

			PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
			paymentRequestDTO.setAmount(amount);
			paymentRequestDTO.setAuthorizerAccountId(toAccountModel.getAccountNo());
			paymentRequestDTO.setAuthorizerMemberId(toMemberId);
			paymentRequestDTO.setDate(new Date());
			paymentRequestDTO.setMerchantAccountId(fromAccountModel.getAccountNo());
			paymentRequestDTO.setMerchantFirstName(fromAccountModel.getFirstName());
			paymentRequestDTO.setMerchantLastName(fromAccountModel.getLastName());
			paymentRequestDTO.setMerchantMemberId(displayUserAccount(fromMemberId));
			paymentRequestDTO.setPaymentId(ExternalUserHelper.generateRandomNumber());
			paymentRequestDTO.setStatus(initiateStatus);
			paymentRequestDTO.setDescription(description);
			if(manageExternalUserAccountDAO.addPaymentRequest(paymentRequestDTO))
				return "success";
			else
				return "failed";
		}
		
		
		else if(initiaterType.getUserTypeId()==322)
			{
				initiateStatus="AUTHORIZED";
				String debitResult = makeDebitTransaction(fromMemberId,amount,toMemberId,"payment");
				if (debitResult.equals("success")) 
				{
					AccountModel customerAccount = getAccountDetails(fromMemberId);
					AccountModel merchantAccount = getAccountDetails(toMemberId);
					

					PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
					paymentRequestDTO.setAmount(amount);
					paymentRequestDTO.setAuthorizerAccountId(customerAccount.getAccountNo());
					paymentRequestDTO.setAuthorizerMemberId(fromMemberId);
					paymentRequestDTO.setDate(new Date());
					paymentRequestDTO.setMerchantAccountId(toAccount);
					paymentRequestDTO.setMerchantFirstName(merchantAccount.getFirstName());
					paymentRequestDTO.setMerchantLastName(merchantAccount.getLastName());
					paymentRequestDTO.setMerchantMemberId(displayUserAccount(toMemberId));
					paymentRequestDTO.setPaymentId(ExternalUserHelper.generateRandomNumber());
					paymentRequestDTO.setStatus(initiateStatus);
					paymentRequestDTO.setDescription(description);
					if(manageExternalUserAccountDAO.addPaymentRequest(paymentRequestDTO))
						return "success";
					else
						return "failed";
				}
				else
					return debitResult;
			}
		else 
			return "Illegle UserType";
		
		
	}

	@Transactional
	public String submitPayment(long paymentId) {
		// TODO Auto-generated method stub
		PaymentRequestDTO paymentDTO =  manageExternalUserAccountDAO.getPaymentRequestByPaymentId(paymentId);
		paymentDTO.setStatus("PENDING_BANK");
		if (manageExternalUserAccountDAO.updatePaymentRequest(paymentDTO))
			return "success";
		else
			return "failed";

	}
	
	@Override
	@Transactional
	public List<TransactionDTO> getApprovedTransactionListForUser(int memberId) {
		// TODO Auto-generated method stub
		UserDTO userDTO = manageExternalUserAccountDAO.displayUserAccountDAO(memberId);
		List<TransactionDTO> transactionList = null;
		if(userDTO != null)
		{
			List<TransactionDTO> tempList = userDTO.getTransactionDTOList();
			if(tempList != null)
			{
				transactionList = new ArrayList<TransactionDTO>();
				for(TransactionDTO transaction : tempList)
				{
					int numdays = ExternalUserHelper.getNumDays(new Date(), transaction.getProcessedDate());
					if(transaction.getIsAuthorized() == true && numdays <= 2)
					{
						transactionList.add(transaction);
					}
				}
			}
			
		}
		return transactionList;
	}

	@Override
	@Transactional
	public boolean findAccount(long accountNumber) {
		// TODO Auto-generated method stub
		boolean result =  manageExternalUserAccountDAO.findAccount( accountNumber);
		return result;
	}

	

}
