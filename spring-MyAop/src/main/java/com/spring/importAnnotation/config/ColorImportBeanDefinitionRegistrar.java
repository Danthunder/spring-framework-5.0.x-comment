package com.spring.importAnnotation.config;

import com.spring.importAnnotation.entity.Black;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Wang danning
 * @since 2020-03-20 23:27
 **/
public class ColorImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		registry.registerBeanDefinition("black", new RootBeanDefinition(Black.class));
	}
}
