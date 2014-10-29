package edu.asu.safemoney.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.service.EmployeeUserService;

@Controller
@SessionAttributes
public class EmployeeUserController {
	
	@Autowired
	private EmployeeUserService employeeUserService;
	
	@RequestMapping("/internal/sendViewRequests")
	public ModelAndView getInternalUserAccountRequests()
	{	
		return new ModelAndView("/internal/EmpRequestCustView");
	}
	
	@RequestMapping("requestTransactionAccess")
	public ModelAndView sendViewRequests(@RequestParam("userName") String userName, HttpServletRequest request, HttpSession sessionID)
	{
		boolean isUserNameAvailable = false;
		if(userName!=null)
		{
			isUserNameAvailable=true;
			if(userName.length()>11 )
			{
				System.out.println("Member Id is not within required length");
			}
			if(!userName.matches("^[1-9][0-9]*$"))
			{
				System.out.println("Member Id is not all numbers");
			}
		}
		
		if(isUserNameAvailable)
		{
			sessionID.setAttribute("userName", userName);
		}
		else
		{

		}
		
		return new ModelAndView("");
	}

}
