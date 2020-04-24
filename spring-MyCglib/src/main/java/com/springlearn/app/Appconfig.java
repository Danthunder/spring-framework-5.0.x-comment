package com.springlearn.app;

import com.springlearn.aop.BeforeAdvice;
import com.springlearn.dao.CityDao;
import com.springlearn.dao.IndexDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;

/**
 * @program: spring.com.springlearn.app.Appconfig
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-05 12:30
 * @version: 1.0
 **/

// 扫描com.springlearn路径下的所有bean
@ComponentScan("com.springlearn.spring")
@Configuration
//@EnableMyAop
@EnableAspectJAutoProxy(proxyTargetClass = true)
@MapperScan
public class Appconfig {

//	@Bean
//	public CityDao cityDao() {
//		return new CityDao();
//	}
//
//	@Bean
//	public IndexDao indexDao() {
////		cityDao();
//		return new IndexDao();
//	}

}
