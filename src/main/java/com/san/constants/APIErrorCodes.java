package com.san.constants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.*;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.san.util.PropertyHelperUtil;

public enum APIErrorCodes {

	// System Exceptions
	Authentication_Exception(AuthenticationException.class, -10001, HttpServletResponse.SC_INTERNAL_SERVER_ERROR), 
	Access_Denied_Exception(AccessDeniedException.class, -10002, HttpServletResponse.SC_UNAUTHORIZED), 
	Bad_Credentials_Exception(BadCredentialsException.class, -10003, HttpServletResponse.SC_UNAUTHORIZED), 
	Internal_Authentication_Service_Exception(InternalAuthenticationServiceException.class, -10004, HttpServletResponse.SC_INTERNAL_SERVER_ERROR), 
	Invalid_Csrf_Token_Exception(InvalidCsrfTokenException.class, -10005, HttpServletResponse.SC_FORBIDDEN), 
	Insufficient_Authentication_Exception(InsufficientAuthenticationException.class, -10006, HttpServletResponse.SC_UNAUTHORIZED), 
	IO_Exception(IOException.class, -10011, HttpStatus.NOT_FOUND.value()), 
	SQL_Exception(SQLException.class, -10012, HttpStatus.BAD_REQUEST.value()), 
	Json_Mapping_Exception(JsonMappingException.class, -10013, HttpStatus.NOT_ACCEPTABLE.value()), 
	Json_Generation_Exception(JsonGenerationException.class, -10014, HttpStatus.NOT_ACCEPTABLE.value()), 
	Servlet_Exception(ServletException.class, -10015, HttpStatus.BAD_REQUEST.value()), 
	Http_Message_Not_Readable_Exception(HttpMessageNotReadableException.class, -10016, HttpStatus.BAD_REQUEST.value());

	public final Class<?> errorClass;
	public final int errorCode;
	public final int responseCode;

	APIErrorCodes(Class<?> errorClass, int errorCode, int responseCode) {
		this.errorClass = errorClass;
		this.errorCode = errorCode;
		this.responseCode = responseCode;

	}

	public static APIErrorCodes getByClass(Class<?> clazz) {
		APIErrorCodes requiredAPIErrorCode = null;
		for (APIErrorCodes apiErrorCode : APIErrorCodes.values()) {
			if (apiErrorCode.errorClass == clazz) {
				requiredAPIErrorCode = apiErrorCode;
				break;
			}
		}
		return requiredAPIErrorCode;
	}

	public String getMessage() {
		return PropertyHelperUtil.getMessage(errorClass.getName());
	}

	public String getMessage(String locale) {
		return PropertyHelperUtil.getMessage(errorClass.getName(), locale);
	}

	public String getMessage(Locale locale) {
		return PropertyHelperUtil.getMessage(errorClass.getName(), locale);
	}

}
