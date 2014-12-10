package com.bazinga.hakaton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 *@author Ivan.Nikolic
 */
@Configuration
@ComponentScan(basePackages = "com.bazinga.hakaton.service")
@EnableTransactionManagement
public class ServiceConfig {

    @Bean
    public JpaTransactionManager transactionManager( DataSource dataSource, EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
