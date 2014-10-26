package edu.asu.safemoney.service.impln;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

	@Autowired
	private LoginDAO loginDAO;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = null;
		System.out.println("userName: " + userName);
		if (userName != null && !userName.isEmpty()) {
			LoginDTO loginDTO = loginDAO.getLoginDetails(userName);
			System.out.println("loginDTO Password: " + loginDTO.getPassword());
			user = getUserFromLoginDTO(loginDTO);
		}
		return user;
	}

	@Transactional
	public User getUserFromLoginDTO(LoginDTO loginDTO) {
		User user = null;
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		UserDTO uDTO = loginDTO.getUserDTO();
		if (loginDTO != null) {
			String userName = loginDTO.getUserName();
			String password = loginDTO.getPassword();
			System.out.println("password" + password);
			boolean isEnabled = true;
			boolean isAcctNonExpired = true;
			boolean isCredentialsNonExpired = true;
			boolean isAcctNonLocked = true;
			if (uDTO != null) {
				System.out.println("not null");
				UserTypeDTO userTypeDTO = uDTO.getUserTypeId();
				System.out.println("getUserType"
						+ userTypeDTO.getUserType());
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						userTypeDTO.getUserType());
				authorityList.add(authority);
				user = new User(userName, password, isEnabled,
						isAcctNonExpired, isCredentialsNonExpired,
						isAcctNonLocked, authorityList);

			}
		}

		return user;
	}

	@Override
	@Transactional
	public String getSiteKeyForUserName(String userName) {
		// TODO Auto-generated method stub
		String siteKey = "";
		if (userName != null && !userName.isEmpty()) {
			siteKey = loginDAO.getSiteKey(userName);
		}
		return siteKey;
	}

	@Transactional
	public boolean createUser(UserModel userModel) {
		String siteKey = loginDAO.getSiteKey(userModel.getUserName());
		if(siteKey != null && !siteKey.isEmpty())
		{

			return false;
		}
		else
		{
			userModel.setCreatedBy("SYSTEM");
			userModel.setCreatedDate(new Date());
			userModel.setExpiryDate(calcuateExpiryDate());
			userModel.setIsActive("true");
			if (userModel.getUserType().equalsIgnoreCase("indCust")) {
				userModel.setUserType("Individual Customer");
				userModel.setUserTypeId(322);
				userModel.setIsCustomer("true");
			} else {
				userModel.setUserType("Merchant/Organization");
				userModel.setUserTypeId(366);
				userModel.setIsCustomer("false");
			}
			loginDAO.createUser(userModel);
			return true;
		}
		
	}

	private Date calcuateExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 365);
		Date expiryDate = cal.getTime();
		return expiryDate;
	}
	
	@Transactional
	public int getMemberId(String userName)
	{
		int memberId = -1;
		LoginDTO loginDTO = loginDAO.getLoginDetails(userName);
		if(loginDTO != null)
		{
			memberId = loginDTO.getMemberId();
		}
		return memberId;
	}
	
	@Transactional
	private boolean validateNewUser(UserModel userModel)
	{
		String siteKey = loginDAO.getSiteKey(userModel.getUserName());
		boolean isEmailExists = loginDAO.isEmailExists(userModel.getEmailId());
		if((siteKey != null && !siteKey.isEmpty()) || isEmailExists)
		{
			return false;
		} else {
			return true;
		}
		
	}

}
