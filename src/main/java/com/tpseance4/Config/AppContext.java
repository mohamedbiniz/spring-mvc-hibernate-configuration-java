package com.tpseance4.Config;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:database.properties"})
public class AppContext {
    @Autowired
    private Environment environment;

    // datasource configuration
    @Bean
    public BasicDataSource datasource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClass"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    // session configuration
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasource());
        sessionFactory.setPackagesToScan(new String[]{
                "com.tpseance4.entities"
        });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    // properties of hibernate
    private Properties hibernateProperties() {
         Properties properties=new Properties();
         properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
         return  properties;
    }

    // transaction manager
    @Bean
    public HibernateTransactionManager getTransctionManager(){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
