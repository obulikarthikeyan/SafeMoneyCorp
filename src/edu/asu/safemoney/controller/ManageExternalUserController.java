package edu.asu.safemoney.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

@Controller
@SessionAttributes
public class ManageExternalUserController {

	@Autowired
	ManageExternalUserAccountService manageExternalUserAccountService;

	// Takes place in the URL for ManageAccount in side menu
	// Populate External user details in the form.
	// Action for the ManageAccount URL in side menu should be
	// "displayExternalUserDetails"
	// Form name in ManageExternalUsers should be "ExternalUserAccountForm"
	// How to populate values ***

	@RequestMapping(value = "/external/displayExternalUserDetails", method = RequestMethod.GET)
	// get userName from session and use @RequestParam
	public ModelAndView populateExternalUserAccount(HttpSession session) {
		int memberId = (Integer) session.getAttribute("memberId");
		UserDTO userDTO = manageExternalUserAccountService
				.displayUserAccount(memberId);
		// Should redirect to "ManageExternalUserAccount.jsp" page
		return new ModelAndView("external/ManageExternalUser").addObject(
				"userDTO", userDTO);
	}

	// Redirect to Update Page
	@RequestMapping(value = "updateButton", method = RequestMethod.GET)
	public String redirectToUpdateExternalUserAccount(ModelMap model) {
		return "shared/UpdateExternalUserAccount";
	}

	// Redirect to Delete Page
	@RequestMapping(value = "deleteButton", method = RequestMethod.GET)
	public String redirectToDeleteExternalUserAccount(ModelMap model) {
		return "shared/DeleteExternalUserAccount";
	}

	// This takes place in "UpdateExternalUserAccount.jsp" page
	// Update User Account. Action for the update button should be
	// "updateExternalUserDetails".
	// Form name should be "ExternalUserUpdateForm".
	@RequestMapping(value = "/external/updateExternalUserDetails", method = RequestMethod.POST)
	public ModelAndView doUpdateAccount(
			@ModelAttribute("updateUser") ModifyUserModel modifyUserModel, ModelMap model) {
		// manageExternalUserAccountService.updateUser(userModel);
		// Should redirect to "updateSuccess.jsp"
		boolean isUpdated = manageExternalUserAccountService.updateUser(modifyUserModel);
		UserDTO userDTO = manageExternalUserAccountService.displayUserAccount(modifyUserModel.getMemberId());
		ModelAndView mv = new ModelAndView("external/ManageExternalUser").addObject("userDTO", userDTO);
		
		if(isUpdated)
		{
			return mv.addObject("message", "Profile Updated Successfully");
		}
		else
		{
			
			return mv.addObject("error", "Update Failed!");
		}		
	}

	// happens when you click the delete account button in the delete page.
	// how can you pass just the User name ***
	@RequestMapping(value = "/deleteExternalUserDetials", method = RequestMethod.POST)
	public ModelAndView doDeleteAccount(HttpSession session) {
		int memberID= (Integer)session.getAttribute("memberId");
		boolean delete= manageExternalUserAccountService.deleteUser(memberID);
		UserDTO userDTO= manageExternalUserAccountService.displayUserAccount(memberID);
		ModelAndView mv= new ModelAndView("external/ManageExternalUser").addObject("UserDTO", userDTO);
		// Should redirect to "updateExternalUserAccount"
		if(delete){
			return mv.addObject("message", "Delete Account request sent");
			
		}
		else{
			return mv.addObject("error", "Delete Request not sent");
		}
	}

	@RequestMapping(value = "/external/transactions", method = RequestMethod.GET)
	public ModelAndView doTransaction(HttpSession session) {
		int memberId = (Integer) session.getAttribute("memberId");
		AccountModel accountModel = manageExternalUserAccountService
				.getAccountDetails(memberId);
		
		return new ModelAndView("external/transactions").addObject("account",
				accountModel);
	}

	@RequestMapping(value = "/external/creditDebit", method = RequestMethod.POST)
	public ModelAndView doCreditOrDebit(
			@RequestParam("creditDebitAmount") double amount,
			@RequestParam("optionsRadiosInline") Integer type,
			HttpSession session)
	// @ModelAttribute("doCreditOrDebit") TransactionModel transaction
	{
		int memberId = (Integer) session.getAttribute("memberId");

		if (type == 1) {
			String result = manageExternalUserAccountService
					.makeCreditTransaction(memberId, amount,memberId);
			System.out.println(type);
			System.out.println(result);
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			if (result.equals("success")) {

				return new ModelAndView("external/transactions").addObject(
						"message", "Credit Transaction Successfull.").addObject("account",
								accountModel);
			} else if (result.equals("failure")) {
				return new ModelAndView("external/transactions").addObject(
						"error", "Credit Transaction failed.").addObject("account",
								accountModel);
			} else if (result.equals("NOFUND")) {
				return new ModelAndView("external/transactions")
						.addObject("error",
								"There is no sufficient fund in your account.").addObject("account",
										accountModel);

			} else if (result.equals("CriticalDebit")){
				return new ModelAndView("external/transactions")
				.addObject("message",
						"This is a critical debit, please wait for authorization").addObject("account",
								accountModel);
			} else if (result.equals("CriticalCredit")){
				return new ModelAndView("external/transactions")
				.addObject("message",
						"This is a critical Credit, please wait for authorization").addObject("account",
								accountModel);
			}
		} else if (type == 2) {
			String result = manageExternalUserAccountService
					.makeDebitTransaction(memberId, amount,memberId);
			System.out.println(type);
			System.out.println(result);
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			if (result.equals("success")) {

				return new ModelAndView("external/transactions").addObject(
						"message", "Debit Transaction Successfull.").addObject("account",
								accountModel);
			} else if (result.equals("failure")) {
				return new ModelAndView("external/transactions").addObject(
						"error", "Debit Transaction failed.").addObject("account",
								accountModel);
			} else if (result.equals("NOFUND")) {
				return new ModelAndView("external/transactions")
						.addObject("error",
								"There is no sufficient fund in your account.").addObject("account",
										accountModel);

			} else if (result.equals("CriticalDebit")){
				return new ModelAndView("external/transactions")
				.addObject("message",
						"This is a critical debit, please wait for authorization").addObject("account",
								accountModel);
			} else if (result.equals("CriticalCredit")){
				return new ModelAndView("external/transactions")
				.addObject("message",
						"This is a critical credit, please wait for authorization").addObject("account",
								accountModel);
			}
		}
		return new ModelAndView("external/transactions");

	}
	
	
	
	@RequestMapping(value = "/external/transfer", method = RequestMethod.POST)
	public ModelAndView doTransform(
			@RequestParam("toAccountNumber") long toAccount,
			@RequestParam("transformAmount") double amount,
			HttpSession session)
	{
		int memberId = (Integer) session.getAttribute("memberId");
		
		String result = manageExternalUserAccountService.makeTransform(memberId, amount,toAccount);
		AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
		if (result.equals("success")) {

			return new ModelAndView("external/transactions").addObject(
					"message", "Transform Transaction Successfull.").addObject("account",
							accountModel);
		} else if (result.startsWith("failure")) {
			return new ModelAndView("external/transactions").addObject(
					"error", "Transform Transaction failed.").addObject("account",
							accountModel);
		} else if (result.startsWith("NOFUND")) {
			return new ModelAndView("external/transactions")
					.addObject("error",
							"There is no sufficient fund in your account.").addObject("account",
									accountModel);
		} else if(result.startsWith("Critical")) {
			return new ModelAndView("external/transactions")
			.addObject("message",
					"This is a critical transaction, please wait for authorization.").addObject("account",
							accountModel);
		}
		return new ModelAndView("external/transactions");
	}

}
