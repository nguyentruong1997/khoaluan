package com.migi.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.migi.validator.EmployeeValidator;

@Configuration
@ComponentScan(basePackages="com.migi")
@PropertySource(value= {"classpath:db.properties"})
@EnableWebMvc
@EnableTransactionManagement
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver; 
	}
	@Override //de truy cap tai nguyen tinh / static resources
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/namemapping/**").addResourceLocations("/Resource/");
	}
	@Bean  // bean de doc file messages1 validator trong resource 
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("classpath:messages1");
		bundleMessageSource.setDefaultEncoding("utf-8");
		return bundleMessageSource;
	}	
	@Bean // tao object custom validator
	public EmployeeValidator EmpValidator() {
		return new EmployeeValidator();
	}
	
	@Autowired
	Environment env;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername("root"); 
		dataSource.setPassword(env.getProperty("password"));
		return dataSource;
	}
	
	@Bean
	 public LocalSessionFactoryBean sessionFactoryBean() {
		 LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		 bean.setDataSource(dataSource());
		 bean.setPackagesToScan("com.migi.entity");
		 Properties hibernateProperties= new Properties();
		 hibernateProperties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
		 hibernateProperties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
		 bean.setHibernateProperties(hibernateProperties);
		 return bean;
	 }
	 @Bean(name="transactionManager")
	 @Autowired
	 public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		 HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		 hibernateTransactionManager.setSessionFactory(sessionFactory);
		 return hibernateTransactionManager;
	 }
	 @Bean(name="multipartResolver")
		public MultipartResolver multipartResolver() {
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
			commonsMultipartResolver.setMaxUploadSize(-1); // -1 ko gioi han , dang byte
			return commonsMultipartResolver;
	 }
	
	
	
	
	
	
}
