package com.san.service.sec;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import com.san.constants.UserRole;
import com.san.to.request.sec.LoginRequestTO;
import com.san.to.response.sec.LoginResponseTO;

@Component
@Lazy
public class SecurityService {

	@Autowired
	AuthenticationManager authenticationManager;

	// Note: Below bean need to be injected only in lazy loaded beans
	@Autowired
	SecurityContextRepository securityContextRepository;

	public LoginResponseTO login(HttpServletRequest request, HttpServletResponse response, LoginRequestTO loginRequestTO) {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequestTO.getUsername(), loginRequestTO.getPassword());

		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(authRequest);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw e;
		}
		if (authentication == null) {
			throw new BadCredentialsException("Bad credentials provided");
		}
		UserDetails user = (UserDetails) authentication.getPrincipal();
		LoginResponseTO responseTO = new LoginResponseTO();
		responseTO.setLocked(!user.isEnabled());
		List<UserRole> authorizations = responseTO.getAuthorizations();
		responseTO.setUsername(user.getUsername());
		for (GrantedAuthority authority : user.getAuthorities()) {
			UserRole role = null;
			try {
				role = UserRole.valueOf(authority.toString().substring(5));
			} catch (Exception e) {
			}
			if (role != null) {
				authorizations.add(role);
			}
		}
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		securityContextRepository.saveContext(securityContext, request, response);
		responseTO.setJSESSIONID(session.getId());
		responseTO.setSuccessMessage(request, "san.security.login");
		return responseTO;
	}
}
