package com.san.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.san.db.CassandraBaseRepository;
import com.san.domain.User;

@Repository
public interface UserRepository extends CassandraBaseRepository<User, MapId>, CassandraRepository<User> {

	@Query("SELECT * FROM User WHERE username = ?0 ALLOW FILTERING")
	List<User> findByUsername(String username);

	@Query("SELECT * FROM User WHERE id = ?0 ALLOW FILTERING")
	List<User> findByID(UUID id);

}
