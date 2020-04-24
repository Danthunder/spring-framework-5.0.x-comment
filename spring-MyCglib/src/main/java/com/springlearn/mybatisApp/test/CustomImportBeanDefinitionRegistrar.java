package com.springlearn.mybatisApp.test;

import com.springlearn.mybatisApp.dao.CardDao;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: spring.com.springlearn.mybatisApp.test.CustomImportBeanDefinitionRegistrar
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-26 00:37
 * @version: 1.0
 **/

public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		System.out.println("CustomImportBeanDefinitionRegistrar");
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CardDao.class);

		GenericBeanDefinition genericBeanDefinition= (GenericBeanDefinition) builder.getBeanDefinition();

//		definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName()); // issue #59
//		definition.setBeanClass(this.mapperFactoryBean.getClass());


		genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(genericBeanDefinition.getBeanClassName());
		genericBeanDefinition.setBeanClass(CustomFactoryBean.class);


		registry.registerBeanDefinition("cardDao", genericBeanDefinition);

	}
}
