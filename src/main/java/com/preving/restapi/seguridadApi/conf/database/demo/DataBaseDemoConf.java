package com.preving.restapi.seguridadApi.conf.database.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Profile("demo")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDemo",
        transactionManagerRef = "transactionManagerDemo",
        basePackages = {"com.preving.restapi.seguridadApi.dao"})
public class DataBaseDemoConf {

    @Value("${datasource.db-gc2006.jndi-name}")
    private String gcJndiName;

    @Primary
    @Bean(name = "dataSourceDemo", destroyMethod = "")
    public DataSource dataSourceDemo() throws Exception {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(this.gcJndiName);
    }

    @Primary
    @Bean(name = "entityManagerFactoryDemo")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDemo(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceDemo") DataSource dataSourceDemo) {
        return builder
                .dataSource(dataSourceDemo)
                .packages("com.preving.restapi.seguridadApi.domain")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerDemo")
    public PlatformTransactionManager transactionManagerDemo(
            @Qualifier("entityManagerFactoryDemo") EntityManagerFactory entityManagerFactoryDemo) {
        return new JpaTransactionManager(entityManagerFactoryDemo);
    }

}