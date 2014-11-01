package edu.asu.safemoney.dao.impln;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.model.UserModel;

@Repository
public class LoginDAOImpl implements LoginDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public String getSiteKey(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM LoginDTO l WHERE l.userName = :userName";
		Query query = session.createQuery(queryString);
		query.setParameter("userName", userName);
		LoginDTO loginDTO = ((LoginDTO) query.uniqueResult());
		String siteKey = null;
		if(loginDTO != null)
		{
			siteKey = loginDTO.getSiteKey();
		}
		System.out.println("SiteKey = " + siteKey);
		return siteKey;
	}

	@Override
	public UserDTO getUserByMemberId(int memberId) 
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM UserDTO u WHERE u.memberId = :memberId";
		Query query = session.createQuery(queryString);
		query.setParameter("memberId", memberId);
		UserDTO userDTO = ((UserDTO) query.uniqueResult());
		return userDTO;
		
	}
	
	@Override
	public LoginDTO getLoginDetails(String userName) 
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM LoginDTO l WHERE l.userName = :userName";
		Query query = session.createQuery(queryString);
		query.setParameter("userName", userName);
		LoginDTO loginDTO = ((LoginDTO) query.uniqueResult());
		return loginDTO;
		
	}
	
	public int getMemberIdByUserName(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM LoginDTO l WHERE l.userName = :userName";
		Query query = session.createQuery(queryString);
		query.setParameter("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		if(loginDTO != null)
		{
			return loginDTO.getUserDTO().getMemberId();
		}
		return -1;
	}
	
	public boolean createUser(UserModel user)
	{
		UserDTO userDTO = copyToUserDTO(user);
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.persist(userDTO);
			
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUserName(user.getUserName());
			loginDTO.setPassword(user.getPassword());
			loginDTO.setSiteKey(user.getSiteKey());
			loginDTO.setUserDTO(userDTO);
			loginDTO.setMemberId(userDTO.getMemberId());
			loginDTO.setFailedAttemptCount(0);
			loginDTO.setIsAccountNonLocked(true);
			loginDTO.setIsEnabled(true);
			userDTO.setLoginDTO(loginDTO);
			
			session.save(userDTO);
			
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public UserDTO copyToUserDTO(UserModel user)
	{
		
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setContactNo(user.getContactNo());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setAddress1(user.getAddress1());
		userDTO.setAddress2(user.getAddress2());
		userDTO.setCity(user.getCity());
		userDTO.setState(user.getState());
		userDTO.setZip(user.getZip());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setAge(user.getAge());
		userDTO.setSsn(user.getSsn());
		userDTO.setIsCustomer(user.getIsCustomer());
		userDTO.setSecQuestion1(user.getSecQuestion1());
		userDTO.setSecAnswer1(user.getSecAnswer1());
		userDTO.setSecQuestion2(user.getSecQuestion2());
		userDTO.setSecAnswer2(user.getSecAnswer2());
		userDTO.setSecQuestion3(user.getSecQuestion3());
		userDTO.setSecAnswer3(user.getSecAnswer3());
		userDTO.setCreatedBy(user.getCreatedBy());
		userDTO.setCreatedDate(user.getCreatedDate());
		userDTO.setExpiryDate(user.getExpiryDate());
		userDTO.setIsActive("true");
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserTypeDTO.findByUserTypeId").setInteger("userTypeId", user.getUserTypeId());
		UserTypeDTO userTypeDTO = (UserTypeDTO) query.uniqueResult();
		
		userDTO.setUserTypeId(userTypeDTO);
		
		RequestDTO requestDTO = new RequestDTO();
		requestDTO.setMemberId(userDTO);
		requestDTO.setAuthorizingAuthority("ADMIN");
		requestDTO.setRequestType("CREATE_ACCOUNT");
		requestDTO.setAuthorityUserTypeId(123);
		requestDTO.setStatus("NEW");
		requestDTO.setRequestDate(new Date());
		requestDTO.setRequestId(ExternalUserHelper.generateRandomNumber());
		
		List<RequestDTO> requestList = new ArrayList<RequestDTO>();
		requestList.add(requestDTO);
		
		userDTO.setRequestDTOList(requestList);
		
		
		System.out.println("fisrtNAme = " + user.getFirstName() + " lastName = "  + user.getFirstName());
		return userDTO;
	}
	
	public boolean isEmailExists(String emailId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByEmailId").setString("emailId", emailId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		if(userDTO != null)
		{
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateLoginFailureAttempts(String userName)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		if(loginDTO != null)
		{
			int failedCount = loginDTO.getFailedAttemptCount();
			if(failedCount >= 3)
			{
				loginDTO.setIsAccountNonLocked(false);
			}
			else
			{
				failedCount += 1;
				loginDTO.setFailedAttemptCount(failedCount);
			}
			session.saveOrUpdate(loginDTO);
			tx.commit();
			session.close();
			return true;
		}
		return false;
		
	} 
	
	public boolean resetFailureAttempts(String userName)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		if(loginDTO != null)
		{
			loginDTO.setFailedAttemptCount(0);
			loginDTO.setIsAccountNonLocked(true);
			session.saveOrUpdate(loginDTO);
			session.close();
			tx.commit();
			return true;
		}
		return false;
	}
	
	public int getFailureAttemptCount(String userName)
	{
		System.out.println("Hi");
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		session.close();
		if(loginDTO != null)
		{
			return loginDTO.getFailedAttemptCount();
		}
		return -1;
	}
	

}
