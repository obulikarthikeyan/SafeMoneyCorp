
package edu.asu.safemoney.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer.AmbiguousBindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.service.AdminUserService;
import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

@Controller
@SessionAttributes
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private EmployeeUserService employeeUserService;
	
	@Autowired
	private ManageExternalUserAccountService manageExternalUserAccountService;
	
	@RequestMapping("/admin/extUserAccount")
	public ModelAndView getExternalUserAccountRequests()
	{	
		List<RequestDTO> requestList= adminUserService.getExterUserAccountRequests();
		return new ModelAndView("/admin/extAccountManagement").addObject("requestList", requestList);
	}
	
	@RequestMapping("/admin/approveExtUserAccount")
	public ModelAndView approveExtUserAccountRequest(
			@RequestParam("requestId") long requestId,
			@RequestParam("requestType") String requestType, @RequestParam("adminAction") String adminAction) {
		ModelAndView mv = new ModelAndView("/admin/extAccountManagement");
		if(adminAction.equals("approve"))
		{
		boolean isApproved = adminUserService.approveExtUserRequest(requestId);
		List<RequestDTO> requestList = adminUserService
				.getExterUserAccountRequests();
		mv.addObject("requestList", requestList);
		if (requestType.equals("CREATE_ACCOUNT")) {
			if (isApproved) {
				mv.addObject("message",
						"Request has been Processed. Bank Account has been created for the user");
			} else {
				mv.addObject("error",
						"Sorry! The request could not be processed.");
			}
			return mv;
		}
		else if(requestType.equals("DELETE_ACCOUNT"))
		{
			if (isApproved) {
				mv.addObject("message",
						"Request has been Processed. The User Account has been deleted");
			} else {
				mv.addObject("error",
						"Sorry! The request could not be processed.");
			}
			return mv;
		}
		return mv;
		}
		else if(adminAction.equals("decline"))
		{
			boolean isDeclined = adminUserService.declineExtUserRequest(requestId);
			List<RequestDTO> requestList = adminUserService
					.getExterUserAccountRequests();
			mv.addObject("requestList", requestList);
			if (requestType.equals("CREATE_ACCOUNT")) {
				if (isDeclined) {
					mv.addObject("message",
							"Request has been Declined");
				} else {
					mv.addObject("error",
							"Sorry! The request could not be processed.");
				}
				return mv;
			}
			else if(requestType.equals("DELETE_ACCOUNT"))
			{
				if (isDeclined) {
					mv.addObject("message",
							"Request has been Declined");
				} else {
					mv.addObject("error",
							"Sorry! The request could not be processed.");
				}
				return mv;
			}
			return mv;
		}
		return mv;
	}
	
	@RequestMapping("/admin/homePage")
	public ModelAndView getAdminHome()
	{
		return new ModelAndView("/admin/home");
	}
	
	@RequestMapping("/admin/intUserAccount")
	public ModelAndView getInternalUserAccountRequests()
	{
		return new ModelAndView("/admin/intAccountManagement");
	}
	
	@RequestMapping("/admin/systemLogPage")
	public ModelAndView getSystemLogPage()
	{
		return new ModelAndView("/admin/viewSystemLog");
	}
	
	@RequestMapping("/admin/piiAuthorization")
	public ModelAndView getPiiAuthorizationPage()
	{
		
		return new ModelAndView("/admin/viewPIIAuthorization");
	}
	
	@RequestMapping("/admin/transactionAuthorizationPage")
	public ModelAndView getTransactionAuthorizationPage()
	{
		return new ModelAndView("/admin/authorizeTransaction");
	}
	
	@RequestMapping("/admin/viewTransactionHistoryPage")
	public ModelAndView viewTransactionHistoryPage(HttpSession session)
	{
		return new ModelAndView("/admin/ExternalUserTransactions");
	}
	
	@RequestMapping(value="/admin/getTransactionHistoryForAdmin", method=RequestMethod.POST)
	public ModelAndView getTransactionHistoryForAdmin(@RequestParam("memberId") int memberId, HttpServletRequest request, HttpSession session)
	{		
		//UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(memberId);
		
		List<TransactionDTO> transactionInfo = employeeUserService.getAllTransactions(memberId);

		return new ModelAndView("/admin/ExternalUserTransactions").addObject("transactionInfo",transactionInfo);
	}
	//authorizeCriticalTransactions
	
	@RequestMapping("/admin/authorizeCriticalTransactions")
	public ModelAndView authorizeCriticalTransactions(HttpSession session)
	{

		List<TransactionDTO> transactionList = adminUserService
				.getTransactionRequest();
		
		return new ModelAndView("/admin/authorizeTransaction").addObject(
						"transactionRequestList", transactionList);
		
	}
	
	@RequestMapping(value="/admin/authorizePaymentRequest", method=RequestMethod.POST)
	public ModelAndView processTransaction(@RequestParam("transactionRequestId") long transactionRequestId,@RequestParam("manageTransactionAction") String manageAction,HttpSession session) 
	{
		
String processResult = null;
		
		if(manageAction.equals("approved"))
		{
			boolean myresult =employeeUserService.updateTransactionRequest( transactionRequestId, "APPROVED_BANK");
			
			TransactionDTO transactiontDTO = employeeUserService.getTransactionDTOById(transactionRequestId);
			
			int toMemberId = employeeUserService.getMemberIdByAccount(transactiontDTO.getToAccount());
			int fromMemberId = employeeUserService.getMemberIdByAccount(transactiontDTO.getFromAccount());
			
			//transactiontDTO.getf
			boolean myresult2 = false;
			String type = transactiontDTO.getTransactionType();
			if(type.equals("Debit"))
			{
				
				myresult2=true;
			}
			else
			{	
				myresult2=employeeUserService.makeCredit(toMemberId,transactiontDTO.getAmount());
			}
			
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
			boolean myresult2=false;
			
			//transactiontDTO.getf
			String type = transactiontDTO.getTransactionType();
			if(type.equals("Debit"))
			{
				myresult2=employeeUserService.makeCredit(fromMemberId,transactiontDTO.getAmount());
			}
			else
			{	
				myresult2 = employeeUserService.makeCredit(fromMemberId,transactiontDTO.getAmount());
			}
			
			if(myresult&&myresult2)
				processResult="You have successfully declined one transaction";
			else
				processResult="Failed";
			
		}
		
		
		List<TransactionDTO> transactionList = adminUserService
				.getTransactionRequest();
		System.out.println(processResult);
		return new ModelAndView("/admin/authorizeTransaction")
		.addObject(
				"transactionRequestList", transactionList).addObject("message",processResult);
	}
}

