package com.springlearn.mybatisApp.app;

import com.springlearn.mybatisApp.anno.MyMapperScan;
import com.springlearn.mybatisApp.test.CustomImportBeanDefinitionRegistrar;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @program: spring.com.springlearn.mybatisApp.Appconfig
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-25 22:55
 * @version: 1.0
 **/
@Configuration
@ComponentScan("com.springlearn.mybatisApp")
//@MyMapperScan("com.springlearn.mybatisApp.dao")
@MapperScan("com.springlearn.mybatisApp.dao")
public class Appconfig {

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

//		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//		configuration.setLogImpl(Log4j2Impl.class);
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setPassword("123456");
		dataSource.setUsername("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
}
