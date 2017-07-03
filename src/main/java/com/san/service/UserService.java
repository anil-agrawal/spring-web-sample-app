package com.san.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import com.san.repo.UserRepository;

@Component
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CassandraTemplate cassandraTemplate;

}
