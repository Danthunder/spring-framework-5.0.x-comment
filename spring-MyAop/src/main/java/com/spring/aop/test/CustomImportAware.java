package com.spring.aop.test;

import com.spring.aop.anno.EnableMyRedis;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @program: spring.com.spring.aop.test.CustomImportAware
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 13:32
 * @version: 1.0
 **/
//@Configuration
public class CustomImportAware implements ImportAware {

	private String keyValue;

	private String aliveTime;

	private String[] componentScanPathArr;

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {

		Map<String, Object> map = importMetadata.getAnnotationAttributes(EnableMyRedis.class.getName());

		Map<String, Object> map1 = importMetadata.getAnnotationAttributes(ComponentScan.class.getName());

		AnnotationAttributes attrs = AnnotationAttributes.fromMap(map);

		AnnotationAttributes attrs1 = AnnotationAttributes.fromMap(map1);

		keyValue = attrs.getString("keyValue");

		aliveTime = attrs.getString("aliveTime");

		componentScanPathArr = attrs1.getStringArray("value");

		System.out.println(keyValue);

		System.out.println(aliveTime);

		System.out.println(componentScanPathArr[0]);

	}
}
