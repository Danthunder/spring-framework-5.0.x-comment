package com.spring.aop.anno;

import com.spring.aop.test.CustomImportAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @program: spring.com.spring.aop.anno.EnableMyRedis
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 13:30
 * @version: 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Import(CustomImportAware.class)
public @interface EnableMyRedis {
	public String aliveTime();
	public String keyValue();
}
