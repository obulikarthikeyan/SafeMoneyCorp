package edu.asu.safemoney.dao.impln;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;

@Repository
public class ManageExternalUserAccountDAOImpl implements
		ManageExternalUserAccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private LoginDAOImpl loginDAOImpl;

	public void updateUser(UserModel user) {

		UserDTO userDTO = loginDAOImpl.copyToUserDTO(user);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userDTO);
	}

	public UserDTO displayUserAccountDAO(int memberId) {
		// query for user details using userName and save them in a list.
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId")
				.setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		return userDTO;
	}

	public AccountModel getAccountDetails(int memberId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId")
				.setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		AccountDTO accountDTO = userDTO.getAccountDTOList().get(0);
		AccountModel accountModel = new AccountModel();
		accountModel.setAccountNo(accountDTO.getAccountNo());
		accountModel.setAmount(accountDTO.getAmount());
		accountModel.setFirstName(userDTO.getFirstName());
		accountModel.setLastName(userDTO.getLastName());
		return accountModel;
	}

	public AccountDTO copyToAccountDTO(AccountModel account, int memberId) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId")
				.setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		AccountDTO accountDTO = new AccountDTO();

		accountDTO.setAccountNo(account.getAccountNo());
		accountDTO.setAmount(account.getAmount());
		accountDTO.setMemberId(userDTO);
		accountDTO.setIsActive("active");// ??what does active mean?
		// accountDTO.s

		return accountDTO;
		// accountDTO.setIsActive(account.ge); what is active?
		// accountDTO.setMemberId(account.ge); member ID.....
	}

	public boolean createTransaction(TransactionDTO txnDTO) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(txnDTO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateAccountBalance(int memberId, double amount) {
		// TODO Auto-generated method stu

		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId")
				.setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		if (userDTO == null) {
			return false;
		} else {
			AccountDTO accountDTO = userDTO.getAccountDTOList().get(0);
			if (accountDTO == null) {
				return false;
			} else {
				try {
					accountDTO.setAmount(amount);
					session.saveOrUpdate(accountDTO);
					return true;
				} catch (Exception e) {
					return false;
				}
			}

		}

	}

	@Override
	public boolean createAccount(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(accountDTO);
			return true;
		} catch (Exception e) {
			return false;
		}
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
}
