package com.springlearn.aopApp.test;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: spring.com.springlearn.aopApp.test.AopImportSelector
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 01:09
 * @version: 1.0
 **/
public class AopImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{AopBeanPostProcessor.class.getName()};
	}
}
