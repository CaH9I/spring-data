package com.alex.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.alex.***")
@ComponentScan("com.alex")
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        var dataSource = new DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/data");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.alex.model");
        emf.setPersistenceUnitName("entities");
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        var jpaProperties = emf.getJpaPropertyMap();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        jpaProperties.put("hibernate.connection.charSet", "UTF-8");
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", true);
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        return emf;
    }

    @Bean
    public JpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setGenerateDdl(true);
        va.setDatabasePlatform("POSTGRESQL");
        return va;
    }
}
