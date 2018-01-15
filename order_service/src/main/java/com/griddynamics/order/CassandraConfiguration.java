package com.griddynamics.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.mapping.SimpleUserTypeResolver;

@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    private final String keySpace;
    private final String contactPoints;

    @Autowired
    public CassandraConfiguration(@Value("${spring.data.cassandra.keyspace-name}") String keySpace,
                                  @Value("${spring.data.cassandra.contact-points}") String contactPoints) {
        this.keySpace = keySpace;
        this.contactPoints = contactPoints;
    }

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        BasicCassandraMappingContext mappingContext = new BasicCassandraMappingContext();
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), keySpace));
        return mappingContext;
    }

}
