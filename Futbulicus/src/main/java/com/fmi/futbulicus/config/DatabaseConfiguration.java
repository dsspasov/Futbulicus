//package com.fmi.futbulicus.config;
//
//import java.net.URISyntaxException;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//
//@Configuration
//@EnableTransactionManagement
//public class DatabaseConfiguration {
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Autowired
//	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
//	
//	@Bean
//	public DataSource dataSource() throws URISyntaxException {	
//		String username = "postgres";
//		String password = "postgres";
//		String dbUrl = "jdbc:postgresql://localhost:5432/futbulicus";
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		dataSource.setUrl(dbUrl);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		return dataSource;
//	}
//
//	/**
//	 * Declare the JPA entity manager factory.
//	 */
//	@Bean
//	@Autowired
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//		entityManagerFactory.setDataSource(dataSource);
//		entityManagerFactory.setPackagesToScan("com.fmi.futbulicus");
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
//		
//		Properties properties = new Properties();
//		properties.put("hibernate.format_sql", "true");
//		properties.put("shibernate.show_sql", "false");
//		properties.put("hibernate.hbm2ddl.auto", "update");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		properties.put("hibernate.enable_lazy_load_no_trans", "true");
//		
//		entityManagerFactory.setJpaProperties(properties);
//		return entityManagerFactory;
//	}
//
//	/**
//	 * Declare the transaction manager.
//	 */
//	@Bean
//	public JpaTransactionManager transactionManager() {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
//		return transactionManager;
//	}
//
//	/**
//	 * PersistenceExceptionTranslationPostProcessor is a bean post processor
//	 * which adds an advisor to any bean annotated with Repository so that any
//	 * platform-specific exceptions are caught and then rethrown as one Spring's
//	 * unchecked data access exceptions (i.e. a subclass of
//	 * DataAccessException).
//	 */
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
//}
