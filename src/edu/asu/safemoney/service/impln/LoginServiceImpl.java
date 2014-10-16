package edu.asu.safemoney.service.impln;

import java.util.ArrayList;
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
import edu.asu.safemoney.dto.MemberUserTypeMapDTO;
import edu.asu.safemoney.dto.UserDTO;
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
		if(userName != null && !userName.isEmpty())
		{
			LoginDTO loginDTO = loginDAO.getLoginDetails(userName);
			System.out.println("loginDTO Password: " + loginDTO.getPassword());
			user = getUserFromLoginDTO(loginDTO);
		}
		return user;
	}
	
	public User getUserFromLoginDTO(LoginDTO loginDTO)
	{
		User user = null;
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		UserDTO uDTO = loginDTO.getUser();
		if(loginDTO != null)
		{
			String userName = loginDTO.getUserName();
			String password = loginDTO.getPassword();
			System.out.println("password" + password);
			boolean isEnabled = true;
			boolean isAcctNonExpired = true;
			boolean isCredentialsNonExpired = true;
			boolean isAcctNonLocked = true;
			if(uDTO != null)
			{
				List<MemberUserTypeMapDTO> userTypeMapList = uDTO.getMemberUserTypeMapDTOList();
				System.out.println("userTypeMapList");
				if(userTypeMapList != null && userTypeMapList.size() > 0)
				{
					System.out.println("not null");
					MemberUserTypeMapDTO userTypeDTO = userTypeMapList.get(0);
					System.out.println("getUserType" + userTypeDTO.getUserTypeId().getUserType());
					SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userTypeDTO.getUserTypeId().getUserType());
					authorityList.add(authority);
					user = new User(userName, password, isEnabled, isAcctNonExpired, isCredentialsNonExpired, isAcctNonLocked, authorityList);
				}
			}
		}
		
		return user;
	}

	@Override
	@Transactional
	public String getSiteKeyForUserName(String userName) {
		// TODO Auto-generated method stub
		String siteKey = "";
		if(userName != null && !userName.isEmpty())
		{
			siteKey = loginDAO.getSiteKey(userName);
		}
		return siteKey;
	}

}
