package com.san.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

@Component
public class DBCommonService {

	@Autowired
	CassandraTemplate cassandraTemplate;

	long countRecords(Class<?> clazz) {
		return cassandraTemplate.count(clazz);
	}

	List<Object> filterData(MapId filters, Class<?> clazz) {
		StringBuilder query = new StringBuilder();
		query.append("select * from ");
		query.append(clazz.getSimpleName() + " where ");
		int filterCounter = 0;
		for (String filter : filters.keySet()) {
			if (filterCounter > 0) {
				query.append(" and ");
			}
			query.append(filter + "=" + filters.get(filter));
			filterCounter++;
		}
		query.append(" ALLOW FILTERING ; ");
		Iterator<?> iterator = cassandraTemplate.stream(query.toString(), clazz);
		List<Object> records = new ArrayList<Object>();
		while (iterator.hasNext()) {
			records.add(iterator.next());
		}
		return records;
	}

	public void customQuery() {
		Select select = QueryBuilder.select("FIELD_ROOM", "FIELD_TIMESTAMP", "FIELD_USER", "FIELD_MESSAGE").from("TABLE_CHAT");
		select.where(QueryBuilder.eq("FIELD_ROOM", "room"));
		select.setFetchSize(20);
	}

}
