package com.san.rest.sec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.san.service.sec.SecurityService;
import com.san.to.request.sec.LoginRequestTO;
import com.san.to.response.sec.LoginResponseTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@Lazy
@Api(description = "Authorization operations")
@RequestMapping(value = "v1/sec")
public class AuthResource {

	Logger logger = Logger.getLogger(AuthResource.class);

	@Autowired
	SecurityService securityService;

	@Secured({ "ROLE_ANONYMOUS", "IS_AUTHENTICATED_ANONYMOUSLY" })
	@ApiOperation(value = "Login API", notes = "Use to perform login in account")
	@ApiResponses({})
	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public LoginResponseTO login(HttpServletRequest rquest, HttpServletResponse response, @RequestBody LoginRequestTO loginRequestTO) {
		return securityService.login(rquest, response, loginRequestTO);
	}

}
