package edu.asu.safemoney.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/userNameLogin", method=RequestMethod.POST)
	public ModelAndView userNameValidation(@RequestParam("username") String userName, HttpServletRequest request, HttpSession sessionID)
	{
		String siteKey = loginService.getSiteKeyForUserName(userName);
		boolean isUserNameAvailable = false;
		if((siteKey != null) && !siteKey.isEmpty())
		{
			isUserNameAvailable = true;
		}
		if(isUserNameAvailable)
		{
			sessionID.setAttribute("userName", userName);
			return new ModelAndView("/shared/authentication").addObject("siteKey", siteKey);
		}
		else
		{
			return new ModelAndView("/shared/Login").addObject("InvalidUserName", "UserName is invalid. Please enter a valid UserName");
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "shared/home";
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "shared/logout";
	}
	
	@RequestMapping(value="/AuthError", method = RequestMethod.GET)
	public String handleAccessDeniedError(ModelMap model) {
		return "shared/authFailed";
	}

	@RequestMapping(value="/landing", method = RequestMethod.GET)
	public String redirectToLanding(ModelMap model) {
		return "shared/landing";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String redirectToHome(ModelMap model) {
		return "shared/home";
	}

}
