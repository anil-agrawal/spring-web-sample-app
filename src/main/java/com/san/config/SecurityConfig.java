package com.san.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.SecurityContextRepository;

import com.san.service.sec.DBUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;

	@Autowired
	DBUserDetailsService userDetailsService;

	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		if (passwordEncoder == null) {
			passwordEncoder = new BCryptPasswordEncoder();
		}
		return passwordEncoder;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean(name = "securityContextRepository")
	public SecurityContextRepository securityContextRepository() {
		SecurityContextRepository securityContextRepository = null;
		try {
			securityContextRepository = super.getHttp().getSharedObject(SecurityContextRepository.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return securityContextRepository;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.formLogin().loginPage("/sec/login").successHandler(authenticationSuccessHandler).permitAll().usernameParameter("username").passwordParameter("password");

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/sec/accessDenied").permitAll();

		http.logout().logoutUrl("/sec/logout").deleteCookies("JSESSIONID");

//		http.authorizeRequests().antMatchers("/").permitAll();
		
		//Restrict Developer APIs
		http.authorizeRequests().regexMatchers("/api/v2/api-docs.group=dev").fullyAuthenticated();
		http.authorizeRequests().antMatchers("/api/**").permitAll();
		// We will control security using annotations

		// Static files
		http.authorizeRequests().antMatchers("/static/**").permitAll();
		http.authorizeRequests().antMatchers("/plugins/**").permitAll();
		http.authorizeRequests().antMatchers("/views/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/data/**").permitAll();
		http.authorizeRequests().antMatchers("/img/**").permitAll();

		http.authorizeRequests().antMatchers("/ws").authenticated();

		http.authorizeRequests().anyRequest().fullyAuthenticated();

	}
}
