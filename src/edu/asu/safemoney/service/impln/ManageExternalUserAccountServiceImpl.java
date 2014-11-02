package edu.asu.safemoney.service.impln;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;








import org.springframework.web.multipart.MultipartFile;




//import antlr.collections.List;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.TransactionModel;
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
	public String initiatePayment(int memberId, long toAccount, String status, double amount) {
		// TODO Auto-generated method stub
		int toMerchantId = manageExternalUserAccountDAO.getMemberIdByAccount(toAccount);
		
		String debitResult = makeDebitTransaction(memberId,amount,toMerchantId,"payment");
		if (debitResult.equals("success")) 
		{
			AccountModel customerAccount = getAccountDetails(memberId);
			AccountModel merchantAccount = getAccountDetails(memberId);

			PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
			paymentRequestDTO.setAmount(amount);
			paymentRequestDTO.setAuthorizerAccountId(customerAccount.getAccountNo());
			paymentRequestDTO.setAuthorizerMemberId(memberId);
			paymentRequestDTO.setDate(new Date());
			paymentRequestDTO.setMerchantAccountId(toAccount);
			paymentRequestDTO.setMerchantFirstName(merchantAccount.getFirstName());
			paymentRequestDTO.setMerchantLastName(merchantAccount.getLastName());
			paymentRequestDTO.setMerchantMemberId(displayUserAccount(toMerchantId));
			paymentRequestDTO.setPaymentId(ExternalUserHelper.generateRandomNumber());
			paymentRequestDTO.setStatus(status);
			if(manageExternalUserAccountDAO.addPaymentRequest(paymentRequestDTO))
				return "success";
			else
				return "failed";
		}
		else
			return debitResult;
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
					if(transaction.getIsAuthorized() == true)
					{
						int numdays = ExternalUserHelper.getNumDays(new Date(), transaction.getProcessedDate());
						if(numdays <= 2)
						{
							transactionList.add(transaction);
						}
					}
				}
			}
			
		}
		return transactionList;
	}

	@Override
	@Transactional
	public boolean sendTransactionModificationRequest(
			TransactionModel transactionModel, int memberId) {
		// TODO Auto-generated method stub
		UserDTO userDTO = displayUserAccount(memberId);
		if(userDTO != null)
		{
		TransactionReviewDTO reviewDTO = new TransactionReviewDTO();
		reviewDTO.setAmount(transactionModel.getAmount());
		reviewDTO.setAuthorizingAuthorityId(125);
		reviewDTO.setAuthorizingAuthorityType("INT_BANK_EMP");
		reviewDTO.setAuthorizingMemberId(null);
		reviewDTO.setCustMemberId(userDTO);
		reviewDTO.setFromAccount(transactionModel.getFromAccount());
		reviewDTO.setToAccount(transactionModel.getToAccount());
		reviewDTO.setProcessedDate(null);
		reviewDTO.setRequestDate(new Date());
		reviewDTO.setStatus("PENDING_BANK");
		reviewDTO.setTransactionId(transactionModel.getTransactionId());
		reviewDTO.setTransactionReviewId(ExternalUserHelper.generateRandomNumber());
		reviewDTO.setTransactionType(transactionModel.getTransactionType());
		reviewDTO.setReviewType("MODIFY");
		boolean isReviewAdded = manageExternalUserAccountDAO.addTransactionReview(reviewDTO);
		if(isReviewAdded)
		{
			boolean isTransactionUpdated = manageExternalUserAccountDAO.updateTransaction(transactionModel.getTransactionId());
			if(isTransactionUpdated)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		}
		return false;
	}

	@Transactional
	@Override
	public boolean deleteTransaction(long transactionId, int memberId) {
		// TODO Auto-generated method stub
		TransactionDTO transactionDTO = manageExternalUserAccountDAO.getTransactionDTO(transactionId);
		if(transactionDTO != null && transactionDTO.getMemberId().getMemberId() == memberId)
		{
			boolean isDeleted = manageExternalUserAccountDAO.deleteTransaction(transactionDTO);
			if(isDeleted)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public boolean sendTransactionDeletionRequest(long transactionId,
			int memberId) {
		// TODO Auto-generated method stub
		TransactionDTO transactionDTO = manageExternalUserAccountDAO.getTransactionDTO(transactionId);
		if(transactionDTO != null && transactionDTO.getMemberId().getMemberId() == memberId)
		{
		TransactionReviewDTO reviewDTO = new TransactionReviewDTO();
		reviewDTO.setAmount(transactionDTO.getAmount());
		reviewDTO.setAuthorizingAuthorityId(125);
		reviewDTO.setAuthorizingAuthorityType("INT_BANK_EMP");
		reviewDTO.setAuthorizingMemberId(null);
		reviewDTO.setCustMemberId(transactionDTO.getMemberId());
		reviewDTO.setFromAccount(transactionDTO.getFromAccount());
		reviewDTO.setToAccount(transactionDTO.getToAccount());
		reviewDTO.setProcessedDate(null);
		reviewDTO.setRequestDate(new Date());
		reviewDTO.setStatus("PENDING_BANK");
		reviewDTO.setTransactionId(transactionDTO.getTransactionId());
		reviewDTO.setTransactionReviewId(ExternalUserHelper.generateRandomNumber());
		reviewDTO.setTransactionType(transactionDTO.getTransactionType());
		reviewDTO.setReviewType("DELETE");
		boolean isReviewAdded = manageExternalUserAccountDAO.addTransactionReview(reviewDTO);
		if(isReviewAdded)
		{
			boolean isTransactionUpdated = manageExternalUserAccountDAO.updateTransaction(transactionId);
			if(isTransactionUpdated)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		}
		return false;
	}

	@Override
	public boolean writeCertFile(MultipartFile file, String filePath) {
		// TODO Auto-generated method stub
		 if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	 
	                // Creating the directory to store file
	                File dir = new File(filePath + File.separator + "tmpFiles");
	                if (!dir.exists())
	                    dir.mkdirs();
	 
	                // Create the file on server
	                File serverFile = new File(dir.getAbsolutePath()
	                        + File.separator + "temp.cert");
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	            }catch(Exception e)
	            {
	            	e.printStackTrace();
	            }
		 }
		return true;
	}

}
