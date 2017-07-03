package com.san.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.san.constants.UserRole;
import com.san.domain.User;
import com.san.repo.UserRepository;

@Component
public class BootstrapService {

	Logger logger = Logger.getLogger(BootstrapService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void startup() {
		bootstrapAdminUser();
		bootstrapSudoUser();
		logger.debug("Application initialized");
	}

	public void destroy() {
		logger.debug("Application being shutting down");
	}

	private void bootstrapAdminUser() {
		User user = new User();
		user.setEmail("admin@san.com");
		user.setLocked(false);
		user.setName("admin");
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		List<UserRole> roles = new ArrayList<UserRole>();
		roles.add(UserRole.VIEWER);
		roles.add(UserRole.OPERATOR);
		user.setRoles(roles);
		userRepository.save(user);
	}

	private void bootstrapSudoUser() {
		User user = new User();
		user.setEmail("sudo@san.com");
		user.setLocked(false);
		user.setName("sudo");
		user.setUsername("sudo");
		user.setPassword(passwordEncoder.encode("sudo"));
		List<UserRole> roles = new ArrayList<UserRole>();
		roles.add(UserRole.VIEWER);
		roles.add(UserRole.OPERATOR);
		roles.add(UserRole.ADMIN);
		user.setRoles(roles);
		userRepository.save(user);
	}
}
