package com.san.to.response.sec;

import java.util.ArrayList;
import java.util.List;

import com.san.constants.UserRole;
import com.san.to.response.ResponseTO;

public class LoginResponseTO extends ResponseTO {

	private String username;
	private String email;
	private List<UserRole> authorizations = new ArrayList<UserRole>();
	private String JSESSIONID;
	private boolean locked;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserRole> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(List<UserRole> authorizations) {
		this.authorizations = authorizations;
	}

	public String getJSESSIONID() {
		return JSESSIONID;
	}

	public void setJSESSIONID(String JSESSIONID) {
		this.JSESSIONID = JSESSIONID;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
