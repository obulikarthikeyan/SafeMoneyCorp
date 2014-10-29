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

}
