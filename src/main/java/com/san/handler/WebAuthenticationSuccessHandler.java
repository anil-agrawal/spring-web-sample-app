package com.san.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.san.to.SideBarDataTO;

@Component
public class WebAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	Logger logger = Logger.getLogger(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		handleSession(request, response, authentication);
	}

	protected void handleSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
		HttpSession session = httpServletRequest.getSession();
		Object authUser = authentication.getPrincipal();
		if (authUser instanceof UserDetails) {
			session.setAttribute("username", ((UserDetails) authUser).getUsername());
			session.setAttribute("authorities", ((UserDetails) authUser).getAuthorities());
		}

		SideBarDataTO sideBarTO = new SideBarDataTO();
		session.setAttribute("sideBarData", sideBarTO);

		// set our response to OK status
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);

		// since we have created our custom success handler, its up to us to where
		// we will redirect the user after successfully login
		handleTargetURL(httpServletRequest, httpServletResponse, authentication);
	}

	// This implementation is going to determine the URL to redirect the user to after login based on the role of the user:
	protected void handleTargetURL(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		String targetUrl = null;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				targetUrl = "/";
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_OPERATOR")) {
				targetUrl = "/";
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_VIEWER")) {
				targetUrl = "/";
				break;
			} else {
				throw new IllegalStateException();
			}
		}
		return targetUrl;
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
