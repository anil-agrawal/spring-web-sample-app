package com.san.db;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

public class CassandraBaseRepositoryImpl<T> extends SimpleCassandraRepository<T, Serializable> implements CassandraBaseRepository<T, Serializable> {

	private final CassandraOperations operations;
	private final CassandraEntityInformation<T, Serializable> entityInformation;

	public CassandraBaseRepositoryImpl(CassandraEntityInformation<T, Serializable> metadata, CassandraOperations operations) {
		super(metadata, operations);
		this.entityInformation = metadata;
		this.operations = operations;
	}

	public PaginatedData<T> selectBatch(String page) {
		Type type = entityInformation.getJavaType();
		// Create select statement
		Select select = QueryBuilder.select().from(((Class<?>) type).getSimpleName());
		select.setFetchSize(20);

		// If we have a 'next' page set we deserialise it and add it to the select
		// statement
		if (page != null) {
			select.setPagingState(PagingState.fromString(page));
		}

		// Used to map rows to Chat domain objects
		CassandraConverter converter = operations.getConverter();

		// Execute the query
		ResultSet resultSet = operations.getSession().execute(select);

		// Get the next paging state
		PagingState newPagingState = resultSet.getExecutionInfo().getPagingState();
		// The number of rows that can be read without fetching
		int remaining = resultSet.getAvailableWithoutFetching();

		List<T> users = new ArrayList<>(20);

		for (Row row : resultSet) {
			// Convert rows to chat objects
			@SuppressWarnings("unchecked")
			T user = (T) converter.read((Class<T>) type, row);

			users.add(user);

			// If we can't move to the next row without fetching we break
			if (--remaining == 0) {
				break;
			}
		}

		// Serialize the next paging state

		// Return an object with a list of chat messages and the next paging state
		return new PaginatedData<T>(users, newPagingState);
	}

}
