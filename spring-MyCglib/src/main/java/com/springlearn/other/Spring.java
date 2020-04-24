package com.springlearn.other;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @program: spring.com.springlearn.other.Spring
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-18 16:02
 * @version: 1.0
 **/
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(value = "com.springlearn.other",
		excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.springlearn.other.exclude.*"))
public class Spring {

//	@Bean
//	@Autowired
//	public SqlSessionFactoryBean sqlSessionFactoryBean (DataSource dataSource) {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource);
//		return sqlSessionFactoryBean;
//	}

//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setPassword("123456");
//		dataSource.setUsername("root");
//		dataSource.setUrl("jdbc:mysql://localhost:3307/test");
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		return dataSource;
//	}


}
