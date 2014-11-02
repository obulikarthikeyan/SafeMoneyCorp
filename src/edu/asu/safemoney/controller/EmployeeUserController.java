/*
 * Author		:	Nishant Rawat
 * StudentID	:	1206343628
 * Course		:	CSE 545 Software Security
 */
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

import edu.asu.safemoney.dao.EmployeeUserDAO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
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
	private EmployeeUserDAO employeeUserDAO;
	
	@Autowired
	private ManageExternalUserAccountService manageExternalUserAccountService;
	
	
	boolean isRequestSent = false;
	
	public static final Logger logger = Logger.getLogger(LoginController.class);

	
	
	/*
	 * Generates a page that Sends "VIEW ACCOUNT" request from the employee to
	 * the customer or merchant. Upon creating it displays the list of customer
	 * to whom the request has been sent 
	 */
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
		
	/*
	 * Actually sends the View Account request from the employee
	 * to customer
	 */
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
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Maps the page that has the list of VIEW ACCOUNT requests which were approved by the 
	 * customers for 
	 */
	@RequestMapping(value="/internal/viewAccounts")
	public ModelAndView viewAccounts(HttpSession session)
	{	
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> requestList= employeeUserService.getRequestList(currentEmployeeId);
		if(requestList!=null)
		{
			if(!requestList.isEmpty())
			{
				return new ModelAndView("/internal/intAccountManagement").addObject("requestList",requestList);
			}
			else
			{
				return new ModelAndView("/internal/intAccountManagement").addObject("requestList",requestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/intAccountManagement");
		}
		
	}
	
	@RequestMapping(value = "/internal/viewUserAccounts", method = RequestMethod.POST)
	public ModelAndView sendRequestId(@RequestParam("requestId") int requestId, HttpServletRequest request, HttpSession session)
	{
		int customerId = employeeUserService.getCustomerId(requestId);
		UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(customerId);
//		long accountNo = customerDTO.getAccountDTOList().get(0).getAccountNo();
		long accountNo = employeeUserService.getAccountNo(customerId);
		return new ModelAndView("/internal/customerAccount").addObject("customerInfo",customerDTO).addObject("accountNo",accountNo);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/*
	 * To load page that sends transaction view requests
	 * 
	 */
	@RequestMapping(value = "/internal/sendViewTransactionRequests")
	public ModelAndView sendViewTransactionRequests(HttpSession session)
	{
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> transactionRequestList= employeeUserService.getTransactionRequestList(currentEmployeeId);
		if(transactionRequestList!=null)
		{
			if(!transactionRequestList.isEmpty())
			{
				return new ModelAndView("/internal/empCustTransactionRequest").addObject("transactionRequestList",transactionRequestList);
			}
			else
			{
				return new ModelAndView("/internal/empCustTransactionRequest").addObject("transactionRequestList",transactionRequestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/empCustTransctionRequest");
		}
	}
		
	/*
	 * To reload page once transactions have been submitted to show the updated list of requests
	 */
	@RequestMapping(value="/internal/requestTransaction", method=RequestMethod.POST)
	public ModelAndView sendTransactionRequests(@RequestParam("memberId") int memberId, HttpServletRequest request, HttpSession session)
	{
		boolean isUserNameAvailable = false;
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		int internalUserId = memberId;

		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);	
		List<RequestDTO> transactionRequestList = null; 
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
			
			isRequestSent = employeeUserService.sendExtUserTransactionViewRequests(internalUserId, currentEmployeeId);
			transactionRequestList = employeeUserService.getTransactionRequestList(currentEmployeeId);
		}
		else
		{
			
			return new ModelAndView("/internal/empCustTransactionRequest").addObject("error","Invalid Member Id").addObject("transactionRequestList",transactionRequestList);
		}
		
		if(isRequestSent)
		{
			return new ModelAndView("/internal/empCustTransactionRequest").addObject("message","Request Sent to User").addObject("transactionRequestList",transactionRequestList);
		}
		else
		{
			return new ModelAndView("/internal/empCustTransactionRequest").addObject("error","Request Failed").addObject("transactionRequestList",transactionRequestList);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/internal/viewTransactionRequests")
	public ModelAndView viewTransactionRequests(HttpSession session)
	{	
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> requestList= employeeUserService.getTransactionRequestList(currentEmployeeId);
		if(requestList!=null)
		{
			if(!requestList.isEmpty())
			{
				return new ModelAndView("/internal/internalAccountTransaction").addObject("requestList",requestList);
			}
			else
			{
				return new ModelAndView("/internal/internalAccountTransaction").addObject("requestList",requestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/internalAccountTransaction");
		}
		
	}
	
	@RequestMapping(value = "/internal/viewUserTransactions", method = RequestMethod.POST)
	public ModelAndView showTransactionsPage(@RequestParam("requestId") int requestId, HttpServletRequest request, HttpSession session)
	{
		int customerId = employeeUserService.getCustomerId(requestId);
		UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(customerId);
		
		List<TransactionDTO> transactionInfo = employeeUserService.getAllTransactions(customerId);

		return new ModelAndView("/internal/customerTransactionAccount").addObject("transactionInfo",transactionInfo);
	}
	

}