package com.san.handler.rest;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.san.constants.APIErrorCodes;
import com.san.to.response.ResponseTO;
import com.san.util.CommonUtil;

@ControllerAdvice
public class RestExceptionHandler {
	Logger logger = Logger.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(Throwable.class)
	public void handleThrowable(HttpServletRequest request, HttpServletResponse response, Throwable exp) throws JsonGenerationException, JsonMappingException, IOException {
		ResponseTO responseBody = new ResponseTO();
		APIErrorCodes apiErrorCodes = APIErrorCodes.getByClass(exp.getClass());
		if (apiErrorCodes != null) {
			response.setStatus(apiErrorCodes.responseCode);
			responseBody.setMessage(apiErrorCodes.getMessage());
			responseBody.setCode(apiErrorCodes.errorCode);
		} else {
			responseBody.setMessage("Some error occurred");
			response.setStatus(500);
			responseBody.setCode(-500);
		}
		responseBody.setSuccess(false);
		response.setContentType("application/json");
		CommonUtil.writeJson(response.getWriter(), responseBody);
	}
}
