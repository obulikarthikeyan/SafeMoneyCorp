package edu.asu.safemoney.dao.impln;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;


@Repository
public class ManageExternalUserAccountDAOImpl implements ManageExternalUserAccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private LoginDAOImpl loginDAOImpl;
	

	public boolean updateUser(ModifyUserModel modifyUserModel){
		
		UserDTO userDTO= copyToUserDTO(modifyUserModel);
		if(userDTO != null)
		{
			try
			{
				Session session= sessionFactory.getCurrentSession();
				session.saveOrUpdate(userDTO);
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public UserDTO displayUserAccountDAO(int memberId){
		// query for user details using userName and save them in a list. 
		Session session= sessionFactory.getCurrentSession();
		Query query= session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO= (UserDTO)query.uniqueResult();
		return userDTO;
	}
	
	public AccountModel getAccountDetails(int memberId) {
		// TODO Auto-generated method stub
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		AccountDTO accountDTO = userDTO.getAccountDTOList().get(0);
		AccountModel accountModel = new AccountModel();
		accountModel.setAccountNo(accountDTO.getAccountNo());
		accountModel.setAmount(accountDTO.getAmount());
		accountModel.setFirstName(userDTO.getFirstName());
		accountModel.setLastName(userDTO.getLastName());
		return accountModel;
	}
	
	
	public AccountDTO copyToAccountDTO(AccountModel account, int memberId)
	{
		
		
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		AccountDTO accountDTO = new AccountDTO();
		
		accountDTO.setAccountNo(account.getAccountNo());
		accountDTO.setAmount(account.getAmount());
		accountDTO.setMemberId(userDTO);
		accountDTO.setIsActive("active");//??what does active mean?
		//accountDTO.s
		
		return accountDTO;
		//accountDTO.setIsActive(account.ge); what is active?
		//accountDTO.setMemberId(account.ge); member ID.....
	}
	
	
	public boolean createTransaction(TransactionDTO txnDTO)
	{
		try
		{
			Session session= sessionFactory.getCurrentSession();
			session.save(txnDTO);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateAccountBalance(int memberId, double amount) {
		// TODO Auto-generated method stu
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		if(userDTO == null)
		{
			return false;
		}else
		{
			AccountDTO accountDTO = userDTO.getAccountDTOList().get(0);
			if(accountDTO == null)
			{
				return false;
			}
			else
			{
				try
				{
					accountDTO.setAmount(amount);
					session.saveOrUpdate(accountDTO);
					return true;
				}
				catch(Exception e)
				{
					return false;
				}
			}
			
		}
		
	}

	@Override
	public boolean createAccount(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(accountDTO);
			return true;
		} catch(Exception e)
		{
			return false;
		}
	}
	

	public UserDTO copyToUserDTO(ModifyUserModel modifyUserModel)
	{
		UserDTO userDTO = displayUserAccountDAO(modifyUserModel.getMemberId());
		if(userDTO != null)
		{
			userDTO.setContactNo(modifyUserModel.getContactNo());
			userDTO.setEmailId(modifyUserModel.getEmailId());
			userDTO.setAddress1(modifyUserModel.getAddress1());
			userDTO.setAddress2(modifyUserModel.getAddress2());
			userDTO.setCity(modifyUserModel.getCity());
			userDTO.setState(modifyUserModel.getState());
			userDTO.setZip(modifyUserModel.getZip());
			return userDTO;
		}
		return null;
	}
		
	public int getMemberIdByAccount(long accountNumber)
	{
		BigInteger bi = BigInteger.valueOf(accountNumber);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("AccountDTO.findByAccountNo").setBigInteger("accountNo", bi);
		AccountDTO accountDTO = (AccountDTO) query.uniqueResult();
		UserDTO user = accountDTO.getMemberId();
		int memberId=user.getMemberId();
		return memberId;
	}
	
	@Override
	public boolean deleteExtUserAccount(int memberId) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery("UserDTO.findByMemberId")
					.setInteger("memberId", memberId);
			UserDTO userDTO = (UserDTO) query.uniqueResult();
			if (userDTO != null) {
				session.delete(userDTO);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
	
	
	@Override
	public List<PaymentRequestDTO> getPaymentRequest(int memberId)
	{
		List<PaymentRequestDTO> paymentRequest = new ArrayList<PaymentRequestDTO>();
		
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("PaymentRequestDTO.findByAuthorizerMemberId").setInteger("authorizerMemberId", memberId);
		List requests = query.list();
		if(requests != null)
		{
			for(Object request : requests)
			{	
				PaymentRequestDTO paymentRequestDTO = (PaymentRequestDTO) request;
				paymentRequest.add(paymentRequestDTO);
			}
		}
		
		return paymentRequest;
		/*List<RequestDTO> requestList = new ArrayList<RequestDTO>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("RequestDTO.findByAuthorityUserTypeId").setInteger("authorityUserTypeId", 123);
		List requests = query.list();
		if(requests != null)
		{
			for(Object request : requests)
			{	
				RequestDTO requestDTO = (RequestDTO) request;
				requestList.add(requestDTO);
			}
		}
		
		return requestList;*/
	}

	@Override
	public PaymentRequestDTO getPaymentRequestByPaymentId(long paymentId) {
		// TODO Auto-generated method stub
		
		//PaymentRequestDTO paymentRequestDTO =  
		BigInteger requestId = BigInteger.valueOf(paymentId);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("PaymentRequestDTO.findByPaymentId").setBigInteger("paymentId", requestId);
		PaymentRequestDTO paymentRequestDTO =   (PaymentRequestDTO) query.uniqueResult();
		return paymentRequestDTO;
		
		
	}

	@Override
	public boolean updatePaymentRequest(PaymentRequestDTO paymentDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.saveOrUpdate(paymentDTO);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean addPaymentRequest(PaymentRequestDTO paymentRequestDTO) {
		// TODO Auto-generated method stub
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(paymentRequestDTO);
			return true;
		} catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean addTransactionReview(
			TransactionReviewDTO transactionReviewDTO) {
		// TODO Auto-generated method stub
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(transactionReviewDTO);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateTransaction(long transactionId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionDTO.findByTransactionId").setLong("transactionId", transactionId);
		try
		{
			TransactionDTO transactionDTO = (TransactionDTO) query.uniqueResult();
			transactionDTO.setStatus("UNDER_REVIEW");
			session.saveOrUpdate(transactionDTO);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TransactionDTO getTransactionDTO(long transactionId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionDTO.findByTransactionId").setLong("transactionId", transactionId);
		TransactionDTO transactionDTO = null;
		try
		{
			transactionDTO = (TransactionDTO) query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return transactionDTO;
	}

	@Override
	public boolean deleteTransaction(TransactionDTO transactionDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.delete(transactionDTO);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
