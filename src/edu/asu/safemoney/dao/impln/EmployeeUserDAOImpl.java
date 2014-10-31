package edu.asu.safemoney.dao.impln;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.EmployeeUserDAO;

@Repository
public class EmployeeUserDAOImpl implements EmployeeUserDAO{
	@Autowired
	private SessionFactory sessionFactory;
}
