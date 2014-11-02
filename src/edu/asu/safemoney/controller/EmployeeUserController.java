package edu.asu.safemoney.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
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
	ManageExternalUserAccountService manageExternalUserAccountService;
	
//	@Autowired
//	private ManageExternalUserAccountServiceImpl manageExternalUserAccountServiceImpl;
	
	boolean isRequestSent = false;
	
	public static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping("/internal/sendViewRequests")
	public ModelAndView getInternalUserAccountRequests(HttpSession session)
	{	
		
		
		UserDTO empUserDTO = new UserDTO((Integer)session.getAttribute("memberId"));
		List<RequestDTO> requestList= empUserDTO.getRequestDTOList();

		
		return new ModelAndView("/internal/EmpRequestCustView");
	}
	
	
	//Added by Fei
	@RequestMapping(value="/internal/manageTransactionRequest", method=RequestMethod.GET)
	public ModelAndView getTransactionRequest(HttpSession session) {

		List<PaymentRequestDTO> paymentList = employeeUserService
				.getPaymentRequest();
		List<TransactionDTO> transactionList = employeeUserService
				.getTransactionRequest();
		
		return new ModelAndView("/internal/EmpManageExtTransactions")
				.addObject("paymentRequestList", paymentList).addObject(
						"transactionRequestList", transactionList);
	}
	
	
	@RequestMapping(value="/internal/processPaymentRequest", method=RequestMethod.POST)
	public ModelAndView processPayment(@RequestParam("paymentRequestId2") long paymentRequestId,@RequestParam("managePaymentAction") String manageAction,HttpSession session) 
	{
		
		String processResult = null;
		
		if(manageAction.equals("approved"))
		{
			boolean myresult =employeeUserService.updatePaymentRequest( paymentRequestId, "APPROVED_BANK");
			//
			PaymentRequestDTO paymentDTO = employeeUserService.getPaymentDTOById(paymentRequestId);
			//
			UserDTO metchantDTO = paymentDTO.getMerchantMemberId();
			
			boolean myresult2 = employeeUserService.makeCredit(metchantDTO.getMemberId(),paymentDTO.getAmount());
			
			if(myresult&&myresult2)
			{
				processResult="You have successfully approved one payment request";
			}
			else
			{
				processResult = "Approve payment request failed";
			}
		}
		else if(manageAction.equals("declined"))
		{
			boolean myresult =employeeUserService.updatePaymentRequest( paymentRequestId, "DECLINED_BANK");
			PaymentRequestDTO paymentDTO = employeeUserService.getPaymentDTOById(paymentRequestId);
			
			boolean myresult2 = employeeUserService.makeCredit(paymentDTO.getAuthorizerMemberId(),paymentDTO.getAmount());
			
			if(myresult&&myresult2)
			{
				processResult="You have successfully approved one payment request";
			}
			else
			{
				processResult = "Approve payment request failed";
			}
		}
		
		List<PaymentRequestDTO> paymentList = employeeUserService
				.getPaymentRequest();
		List<TransactionDTO> transactionList = employeeUserService
				.getTransactionRequest();
		
		return new ModelAndView("/internal/EmpManageExtTransactions")
		.addObject("paymentRequestList", paymentList).addObject(
				"transactionRequestList", transactionList).addObject("message",processResult);
	}
	
	
	
	@RequestMapping(value="/internal/authorizePaymentRequest", method=RequestMethod.POST)
	public ModelAndView processTransaction(@RequestParam("transactionRequestId") long transactionRequestId,@RequestParam("manageTransactionAction") String manageAction,HttpSession session) 
	{
		
		String processResult = null;
		
		if(manageAction.equals("approved"))
		{
			boolean myresult =employeeUserService.updateTransactionRequest( transactionRequestId, "APPROVED_BANK");
			System.out.println(2);
			TransactionDTO transactiontDTO = employeeUserService.getTransactionDTOById(transactionRequestId);
			System.out.println(3);
			int toMemberId = employeeUserService.getMemberIdByAccount(transactiontDTO.getToAccount());
			System.out.println(4);
			//transactiontDTO.getf
			boolean myresult2 = employeeUserService.makeCredit(toMemberId,transactiontDTO.getAmount());
			System.out.println(5);
			if(myresult&&myresult2)
				processResult="You have successfully approved one transaction";
			else
				processResult="Failed";
			
			
			
		}
		else if(manageAction.equals("declined"))
		{
			boolean myresult =employeeUserService.updateTransactionRequest( transactionRequestId, "DECLINED_BANK");
			
			TransactionDTO transactiontDTO = employeeUserService.getTransactionDTOById(transactionRequestId);
			
			int fromMemberId = employeeUserService.getMemberIdByAccount(transactiontDTO.getFromAccount());
			
			//transactiontDTO.getf
			boolean myresult2 = employeeUserService.makeCredit(fromMemberId,transactiontDTO.getAmount());
			
			if(myresult&&myresult2)
				processResult="You have successfully declined one transaction";
			else
				processResult="Failed";
			
		}
		
		List<PaymentRequestDTO> paymentList = employeeUserService
				.getPaymentRequest();
		List<TransactionDTO> transactionList = employeeUserService
				.getTransactionRequest();
		System.out.println(processResult);
		return new ModelAndView("/internal/EmpManageExtTransactions")
		.addObject("paymentRequestList", paymentList).addObject(
				"transactionRequestList", transactionList).addObject("message",processResult);
	}
	
	
	//End Add by Fei
	@RequestMapping(value="/internal/requestTransactionAccess", method=RequestMethod.POST)
	public ModelAndView sendViewRequests(@RequestParam("memberId") int memberId, HttpServletRequest request, HttpSession session)
	{
		boolean isUserNameAvailable = false;
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
			int currentEmployeeId = (Integer)session.getAttribute("memberId");
			int internalUserId = memberId;
			
			isRequestSent = employeeUserService.sendExtUserViewRequests(internalUserId, currentEmployeeId);			
		}
		else
		{
			return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Invalid Member Id");
		}
		
		if(isRequestSent)
		{
			return new ModelAndView("/internal/EmpRequestCustView").addObject("message","Request Sent to User");
		}
		else
		{
			return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Request Failed");
		}
	}

}