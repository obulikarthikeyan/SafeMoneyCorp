package edu.asu.safemoney.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.LoginService;

@Controller
@SessionAttributes
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value="/userNameLogin", method=RequestMethod.POST)
	public ModelAndView userNameValidation(@RequestParam("userName") String userName, HttpServletRequest request, HttpSession sessionID)
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
		return "shared/home";
	}
	
	@RequestMapping(value="/AuthError", method = RequestMethod.GET)
	public String handleAccessDeniedError(ModelMap model) {
		return "shared/authFailed";
	}

	@RequestMapping(value="/landing", method = RequestMethod.GET)
	public String redirectToLanding(ModelMap model, HttpSession session) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int memberId = loginService.getMemberId(user.getUsername());
		session.setAttribute("memberId", memberId);
		return "shared/landing";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String redirectToHome(ModelMap model) {
		return "shared/home";
	}
	
	@RequestMapping(value="/signUp", method = RequestMethod.POST)
	public String redirectToSignUp(ModelMap model) {
		return "shared/signup";
	}
	
	@RequestMapping(value="/userSignUp", method = RequestMethod.POST)
	public ModelAndView doUserSignUp(@ModelAttribute("signUpForm") UserModel userModel)
	{
		System.out.println("date = " + userModel.getDateOfBirth());
		boolean isSuccess = loginService.createUser(userModel);
		if(!isSuccess)
		{
			return new ModelAndView("shared/signup").addObject("signUpForm", userModel);
		}
		else
		{
			return new ModelAndView("shared/home");
		}
	}
	

}
