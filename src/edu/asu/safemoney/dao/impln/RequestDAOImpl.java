package edu.asu.safemoney.dao.impln;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.RequestDTO;

@Repository
public class RequestDAOImpl implements RequestDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean generateRequest(RequestDTO requestDTO)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(requestDTO);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RequestDTO> getRequestsForAdminUser() {
		List<RequestDTO> requestList = new ArrayList<RequestDTO>();
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
		
		return requestList;
	}
	
}
