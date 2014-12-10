package com.bazinga.hakaton.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *@author Ivan.Nikolic
 */
@Configuration
@EnableJpaRepositories(basePackages="com.bazinga.hakaton.repository")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class RepositoryConfig {

    public static final String MODEL_CLASSES_LOCATION = "com.bazinga.hakaton.model";
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws IOException, SQLException, PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(env.getProperty("db.driverClass"));
        cpds.setJdbcUrl(env.getProperty("db.jdbcUrl"));
        cpds.setUser(env.getProperty("db.user"));
        cpds.setPassword(env.getProperty("db.password"));
        configureC3p0(cpds);
        return cpds;
    }

    private void configureC3p0(ComboPooledDataSource cpds){
        cpds.setMinPoolSize(getIntProperty("c3p0.setMinPoolSize"));
        cpds.setAcquireIncrement(getIntProperty("c3p0.setAcquireIncrement"));
        cpds.setMaxPoolSize(getIntProperty("c3p0.setMaxPoolSize"));
        cpds.setMaxStatements(getIntProperty("c3p0.setMaxStatements"));
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(getDatabaseVendor("hibernate.database.vendor"));
        adapter.setShowSql(getBooleanProperty("hibernate.show.sql"));
        adapter.setGenerateDdl(getBooleanProperty("hibernate.generate.ddl"));
        adapter.setDatabasePlatform(env.getProperty("hibernate.database.platform"));
        return adapter;
    }

    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan(MODEL_CLASSES_LOCATION);
        return emfb;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Integer getIntProperty(String key){
        return Integer.valueOf(env.getProperty(key));
    }

    private Boolean getBooleanProperty(String key) {
        return Boolean.valueOf(env.getProperty(key));
    }

    private Database getDatabaseVendor(String key){
        String dbName = env.getProperty(key);
        for (Database d : Database.values()){
            if (d.name().equals(dbName)){
                return d;
            }
        }
        throw new IllegalArgumentException("Unable to find database with name: " + dbName);
    }
}
