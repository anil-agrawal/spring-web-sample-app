package com.san.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
	Logger logger = Logger.getLogger(this.getClass());

	@Secured({ "ROLE_VIEWER" })
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
}
