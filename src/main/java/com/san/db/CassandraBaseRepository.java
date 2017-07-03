package com.san.db;

import java.io.Serializable;
import java.lang.reflect.Type;
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;

import com.google.common.reflect.TypeToken;

public interface CassandraBaseRepository<T, ID extends Serializable> extends TypedIdCassandraRepository<T, ID> {

	@SuppressWarnings("serial")
	default public Type fetchType() {
		return (new TypeToken<T>(getClass()) {
		}).getType();
	}

	public PaginatedData<T> selectBatch(String page);
}
