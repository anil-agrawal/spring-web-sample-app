package com.san.service.sec;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.san.constants.UserRole;
import com.san.domain.User;
import com.san.repo.UserRepository;

@Component
public class DBUserDetailsService implements UserDetailsService {

	Logger logger = Logger.getLogger(DBUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findByUsername(username);
		if (users == null || users.size() < 1) {
			logger.trace("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		User user = users.get(0);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), !user.isLocked(), true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<UserRole> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			for (UserRole role : roles) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
			}
		}
		logger.trace("authorities :" + authorities);
		return authorities;
	}

}
