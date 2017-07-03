package com.san.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.san.util.SecurityUtil;

@Controller
@RequestMapping(value = "test")
public class TestController {

	Logger logger = Logger.getLogger(TestController.class);

	@Secured("ROLE_VIEWER")
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(ModelMap model) {
		model.addAttribute("greeting", "Hi " + SecurityUtil.getUsername() + ", Welcome to this site. You are accessing a page allowed to VIEWER. \n\n");
		return "sec/welcome";
	}

	@Secured("ROLE_OPERATOR")
	@RequestMapping(value = "operate", method = RequestMethod.GET)
	public String operate(ModelMap model) {
		model.addAttribute("greeting", "Hi " + SecurityUtil.getUsername() + ", Welcome to this site. You are accessing a page allowed to OPERATOR. \n\n");
		return "sec/welcome";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String admin(ModelMap model) {
		model.addAttribute("greeting", "Hi " + SecurityUtil.getUsername() + ", Welcome to this site. You are accessing a page allowed to ADMIN. \n\n");
		return "sec/welcome";
	}

	@Secured({ "ROLE_ANONYMOUS", "IS_AUTHENTICATED_ANONYMOUSLY" })
	@RequestMapping(value = "other", method = RequestMethod.GET)
	public String other(ModelMap model) {
		model.addAttribute("greeting", "Hi " + SecurityUtil.getUsername() + ", Welcome to this site. You are accessing a page allowed to ANONYMOUS user. \n\n");
		return "sec/welcome";
	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmeticExceptionException(ArithmeticException ex) {
		ModelAndView modelView = new ModelAndView("error/404", "message", "Page Not Found");
		return modelView;
	}

}
