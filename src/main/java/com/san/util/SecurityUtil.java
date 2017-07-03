//-----------------------------------------------------------------------------------------------------------
//					ORGANIZATION NAME
//Group							: Common - Project
//Product / Project				: ass-common
//Module						: ass-common
//Package Name					: com.san.common.util
//File Name						: SecurityUtil.java
//Author						: anil
//Contact						: anilagrawal038@gmail.com
//Date written (DD MMM YYYY)	: 22-Jan-2017 10:55:15 PM
//Description					:  
//-----------------------------------------------------------------------------------------------------------
//					CHANGE HISTORY
//-----------------------------------------------------------------------------------------------------------
//Date			Change By		Modified Function 			Change Description (Bug No. (If Any))
//(DD MMM YYYY)
//22-Jan-2017   	anil			N/A							File Created
//-----------------------------------------------------------------------------------------------------------

package com.san.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;

import com.san.service.sec.SecurityService;

public class SecurityUtil {

	static Logger logger = Logger.getLogger(SecurityUtil.class);

	private static MessageDigest messageDigest = null;
	private static SecureRandom random = new SecureRandom();

	public static String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public static UserDetails fetchCurrentUserDetails() {
		UserDetails details = null;
		try {
			try {
				details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
			} catch (Exception e) {
			}
			if (details == null) {
				details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		} catch (Exception e) {
		}
		return details;
	}

	public static String getUsername() {
		String username = null;
		UserDetails details = null;
		try {
			details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		} catch (Exception e) {
		}
		if (details == null) {
			try {
				details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
			}
		}
		if (details != null) {
			username = details.getUsername();
		}
		return username;
	}

	public static void invalidateCurrentSecurityContext() {
		SecurityContextHolder.clearContext();
	}

	public static String generateRandomString() {
		return new BigInteger(130, random).toString(32);
	}

	public static String generateRandomStringWithFixedLength(int length) {
		String randomString = generateRandomString();
		while (randomString.length() < length) {
			randomString += generateRandomString();
		}
		return randomString.substring(randomString.length() - length);
	}

	public static String computeDigest(String input) throws NoSuchAlgorithmException {
		if (messageDigest == null) {
			messageDigest = MessageDigest.getInstance("SHA-1");
		}
		return new String(Base64.encode(messageDigest.digest(input.getBytes())));
	}

	public static SecurityService fetchSecurityService() {
		return CommonUtil.ctx.getBean(SecurityService.class);
	}

}
