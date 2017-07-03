package com.san.domain;

import java.util.UUID;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.utils.UUIDs;

@Table
public class SampleData {

	@PrimaryKey
	@CassandraType(type = DataType.Name.UUID)
	private UUID id = UUIDs.timeBased();

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SampleData [id=" + id + ", name=" + name + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}
