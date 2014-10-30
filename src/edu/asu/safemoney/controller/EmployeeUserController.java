package edu.asu.safemoney.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.model.*;
import edu.asu.safemoney.service.EmployeeUserService;

@Controller
@SessionAttributes
public class EmployeeUserController {
	
	@Autowired
	private EmployeeUserService employeeUserService;
	
	public static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping("/internal/sendViewRequests")
	public ModelAndView getInternalUserAccountRequests()
	{	
		return new ModelAndView("/internal/EmpRequestCustView");
	}
	
	@RequestMapping(value="/internal/requestTransactionAccess", method=RequestMethod.POST)
	public ModelAndView sendViewRequests(@RequestParam("memberId") int memberId, HttpServletRequest request, HttpSession sessionID)
	{
		boolean isUserNameAvailable = false;
		isUserNameAvailable = true;


		
		if(isUserNameAvailable)
		{
			
			UserModel userModel = new UserModel();
		}
		else
		{

		}
		
		return new ModelAndView("");
	}

}
