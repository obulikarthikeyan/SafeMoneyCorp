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
import edu.asu.safemoney.service.AdminUserService;

@Controller
@SessionAttributes
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping("/admin/extUserAccount")
	public ModelAndView getExternalUserAccountRequests()
	{	
		List<RequestDTO> requestList= adminUserService.getExterUserAccountRequests();
		return new ModelAndView("/admin/extAccountManagement").addObject("requestList", requestList);
	}
	
	@RequestMapping("/admin/approveExtUserAccount")
	public ModelAndView approveExtUserAccountRequest(@RequestParam("requestId") long requestId)
	{
		boolean isApproved = adminUserService.approveExtUserRequest(requestId);
		List<RequestDTO> requestList= adminUserService.getExterUserAccountRequests();
		ModelAndView mv = new ModelAndView("/admin/extAccountManagement");
		if(isApproved)
		{
			mv.addObject("message", "Request has been Processed. Bank Account has been created for the user");
		}else
		{
			mv.addObject("error", "Sorry! The request could not be processed.");
		}
		mv.addObject("requestList", requestList);
		return mv;
	}

}
