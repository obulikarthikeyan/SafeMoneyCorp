package edu.asu.safemoney.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
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
	
	public static final Logger logger = Logger.getLogger(LoginController.class);
	
	
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
			int memberId = loginService.getMemberId(userName);
			sessionID.setAttribute("memberId", memberId);
			return new ModelAndView("/shared/authentication").addObject("siteKey", siteKey);
		}
		else
		{
			//sessionID.getAttribute("exception").toString();
			logger.error("UserLogin Attempt - InvalidUserName");
			System.out.println("Home" + System.getProperty("catalina.home"));
			System.out.println("base: " + System.getProperty("catalina.base"));
			return new ModelAndView("/shared/home").addObject("InvalidUserName", "UserName is invalid. Please enter a valid UserName");
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    return "redirect:/landing";
		}
		else
		{
			return "shared/home";
		}
		
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    return "redirect:/landing";
		}else
		{
			return "shared/home";
		}
	}
	
	@RequestMapping(value="/AuthError", method = RequestMethod.GET)
	public String handleAccessDeniedError(ModelMap model) {
		return "shared/authFailed";
	}

	@RequestMapping(value="/landing", method = RequestMethod.GET)
	public String redirectToLanding(ModelMap model, HttpSession session) {
		return "shared/landing";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView redirectToHome(@RequestParam(value = "error", required = false) String error, HttpServletRequest request) {
		if(getAuthErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION") != null)
		{
			return new ModelAndView("shared/home").addObject("error", getAuthErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		else
		{
			return new ModelAndView("shared/home").addObject("authError", "Authentication Failed");
		}
	}
	
	private String getAuthErrorMessage(HttpServletRequest request, String key){
		 
		Exception exception = 
                   (Exception) request.getSession().getAttribute(key);
 
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid Credentials. Authentication Failed";
		}else if(exception instanceof LockedException) {
			error = exception.getMessage();
		}else{
			error = "Invalid username and password!";
		}
 
		return error;
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
