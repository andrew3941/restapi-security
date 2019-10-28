package com.preving.restapi.seguridadApi.conf.database.prod;

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

@Profile("prod")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryProd",
        transactionManagerRef = "transactionManagerProd",
        basePackages = {"com.preving.restapi.seguridadApi.dao"})
public class DataBaseProdConf {

    @Value("${datasource.db-gc2006.jndi-name}")
    private String gcJndiName;

    @Primary
    @Bean(name = "dataSourceProd", destroyMethod = "")
    public DataSource dataSourceProd() throws Exception {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(this.gcJndiName);
    }

    @Primary
    @Bean(name = "entityManagerFactoryProd")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryProd(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceProd") DataSource dataSourceProd) {
        return builder
                .dataSource(dataSourceProd)
                .packages("com.preving.restapi.seguridadApi.domain")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerProd")
    public PlatformTransactionManager transactionManagerProd(
            @Qualifier("entityManagerFactoryProd") EntityManagerFactory entityManagerFactoryProd) {
        return new JpaTransactionManager(entityManagerFactoryProd);
    }

}