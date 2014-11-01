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
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.*;
import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;
import edu.asu.safemoney.service.impln.ManageExternalUserAccountServiceImpl;

@Controller
@SessionAttributes
public class EmployeeUserController {
	
	@Autowired
	private EmployeeUserService employeeUserService;
	
	@Autowired
	ManageExternalUserAccountService manageExternalUserAccountService;
	
//	@Autowired
//	private ManageExternalUserAccountServiceImpl manageExternalUserAccountServiceImpl;
	
	boolean isRequestSent = false;
	
	public static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="/internal/sendViewRequests")
	public ModelAndView getInternalUserAccountRequests(HttpSession session)
	{	
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> requestList= employeeUserService.getRequestList(currentEmployeeId);
		if(requestList!=null)
		{
			if(!requestList.isEmpty())
			{
				return new ModelAndView("/internal/EmpRequestCustView").addObject("requestList",requestList);
			}
			else
			{
				return new ModelAndView("/internal/EmpRequestCustView").addObject("requestList",requestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/EmpRequestCustView");
		}
		
	}
	
	@RequestMapping(value="/internal/requestTransactionAccess", method=RequestMethod.POST)
	public ModelAndView sendViewRequests(@RequestParam("memberId") int memberId, HttpServletRequest request, HttpSession session)
	{
		boolean isUserNameAvailable = false;
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		int internalUserId = memberId;

		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);	
		List<RequestDTO> requestList = null; 
		if (memberId<0)
		{
			logger.error("The member Id cannot be negative");
			isUserNameAvailable=false;
		}
		else
		{
			isUserNameAvailable=true;
		}
		
		if(isUserNameAvailable)
		{		
			
			isRequestSent = employeeUserService.sendExtUserViewRequests(internalUserId, currentEmployeeId);
			requestList = employeeUserService.getRequestList(currentEmployeeId);
		}
		else
		{
			
			return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Invalid Member Id").addObject("requestList",requestList);
		}
		
		if(isRequestSent)
		{
			return new ModelAndView("/internal/EmpRequestCustView").addObject("message","Request Sent to User").addObject("requestList",requestList);
		}
		else
		{
			return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Request Failed").addObject("requestList",requestList);
		}
	}

}