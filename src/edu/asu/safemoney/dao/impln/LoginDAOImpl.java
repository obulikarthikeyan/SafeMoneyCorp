package edu.asu.safemoney.dao.impln;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.UserDTO;

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
			return loginDTO.getUser().getMemberId();
		}
		return -1;
	}

}
