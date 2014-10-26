package edu.asu.safemoney.controller;


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
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

@Controller
public class ManageExternalUserController {

	@Autowired ManageExternalUserAccountService manageExternalUserAccountService;
	
	//Takes place in the URL for ManageAccount in side menu
	// Populate External user details in the form.
	// Action for the ManageAccount URL in side menu should be "displayExternalUserDetails"
	// Form name in ManageExternalUsers should be "ExternalUserAccountForm"
	// How to populate values ***
	
	@RequestMapping(value="/displayExternalUserDetails", method=RequestMethod.GET)
	// get userName from session and use @RequestParam
	public ModelAndView populateExternalUserAccount(HttpSession session) {
		int memberId= (Integer)session.getAttribute("memberId");
		UserDTO userDTO= manageExternalUserAccountService.displayUserAccount(memberId);
		// Should redirect to "ManageExternalUserAccount.jsp" page
		return new ModelAndView("shared/ManageExternalUserAccount").addObject("userDTO",userDTO);	
	}
		
	
	// Redirect to Update Page 
	@RequestMapping(value="updateButton", method=RequestMethod.GET)
	public String redirectToUpdateExternalUserAccount(ModelMap model){
		return "shared/UpdateExternalUserAccount"; 
	}
	
	
	// Redirect to Delete Page
	@RequestMapping(value="deleteButton", method=RequestMethod.GET)
	public String redirectToDeleteExternalUserAccount(ModelMap model){
		return "shared/DeleteExternalUserAccount";
	}
	
	
	// This takes place in "UpdateExternalUserAccount.jsp" page
	// Update User Account. Action for the update button should be "updateExternalUserDetails". 
	// Form name should be "ExternalUserUpdateForm".
	@RequestMapping(value="/updateExternalUserDetials", method= RequestMethod.POST)
	public String doUpdateAccount(@ModelAttribute("ExternalUserUpdateForm") UserModel userModel, ModelMap model) {
		manageExternalUserAccountService.updateUser(userModel);
		// Should redirect to "updateSuccess.jsp"
		return "shared/UpdateSuccessPage"; 
		// can check for fail condition also ***
	}
	
	
	// happens when you click the delete account button in the delete page.
	// how can you pass just the User name ***
	@RequestMapping(value="/deleteExternalUserDetials", method= RequestMethod.POST)
	public String doDeleteAccount(String UserName) {
		manageExternalUserAccountService.deleteUser(UserName);
		// Should redirect to "updateExternalUserAccount"
		return "shared/deleteSuccessPage";
	}
	
	@RequestMapping(value="/external/transactions", method = RequestMethod.GET)
	public ModelAndView doTransaction(HttpSession session)
	{
		int memberId = (Integer) session.getAttribute("memberId");
		AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
		return new ModelAndView("external/transactions").addObject("account", accountModel);
	}
	
}
