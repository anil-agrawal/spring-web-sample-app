package com.san.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.san.db.CassandraBaseRepositoryImpl;

//Reference : http://www.baeldung.com/spring-data-cassandra-tutorial

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = { "com.san.repo" }, repositoryBaseClass = CassandraBaseRepositoryImpl.class)
public class CassandraConfig extends AbstractCassandraConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(CassandraConfig.class);

	@Autowired
	private Environment env;

	@Override
	protected String getKeyspaceName() {
		return env.getProperty("cassandra.keyspace");
	}

	@Override
	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(env.getProperty("cassandra.contactpoints"));
		cluster.setPort(Integer.parseInt(env.getProperty("cassandra.port")));
		LOG.debug("Cassandra cluster factory bean created");
		return cluster;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "com.san.domain" };
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

}