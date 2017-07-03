package com.san.rest;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.san.constants.AppConstants;
import com.san.to.response.ResponseTO;
import com.san.util.SecurityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(description = "Test operations", value = "dev", hidden = true)
@RequestMapping(value = "v1/test")
public class TestRestController {

	Logger logger = Logger.getLogger(this.getClass());

	@Secured("ROLE_VIEWER")
	@ApiOperation(tags = {}, value = "Test Viewer role operation", notes = "API will respond Status code 200, if user is allowed to perform Viewer operations")
	@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized request") })
	@RequestMapping(value = "view/{name}", method = RequestMethod.GET, produces = "application/json")
	public String view(@PathVariable String name) {
		return " hi " + name + ". You are logged in with user : " + SecurityUtil.getUsername() + " !!! You are accessing an api allowed to Viewer";
	}

	@Secured("ROLE_OPERATOR")
	@ApiOperation(tags = {}, value = "Test OPERATOR role operation", notes = "API will respond Status code 200, if user is allowed to perform OPERATOR operations")
	@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized request") })
	@RequestMapping(value = "operate/{name}", method = RequestMethod.GET, produces = "application/json")
	public String operate(@PathVariable String name) {
		return " hi " + name + ". You are logged in with user : " + SecurityUtil.getUsername() + " !!! You are accessing an api allowed to OPERATOR";
	}

	@Secured("ROLE_ADMIN")
	@ApiOperation(tags = {}, value = "Test ADMIN role operation", notes = "API will respond Status code 200, if user is allowed to perform ADMIN operations")
	@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized request") })
	@RequestMapping(value = "admin/{name}", method = RequestMethod.GET, produces = "application/json")
	public String admin(@PathVariable String name) {
		return " hi " + name + ". You are logged in with user : " + SecurityUtil.getUsername() + " !!! You are accessing an api allowed to ADMIN";
	}

	@Secured({ "ROLE_ANONYMOUS", "IS_AUTHENTICATED_ANONYMOUSLY" })
	@ApiOperation(tags = {}, value = "Test ANONYMOUS operation", notes = "API will respond Status code 200 for all users")
	@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized request") })
	@RequestMapping(value = "other/{name}", method = RequestMethod.GET, produces = "application/json")
	public String other(@PathVariable String name) {
		return " hi " + name + ". You are logged in with user : " + SecurityUtil.getUsername() + " !!! You are accessing an api allowed to ANONYMOUS";
	}

}
