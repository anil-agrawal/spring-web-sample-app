package com.san.repo;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.san.db.CassandraBaseRepository;
import com.san.domain.SampleData;

@Repository
public interface SampleDataRepository extends CassandraBaseRepository<SampleData, UUID> {

}
