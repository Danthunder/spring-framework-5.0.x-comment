package com.spring.importAnnotation;

import com.spring.importAnnotation.config.ColorConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

/**
 * @author Wang danning
 * @since 2020-03-20 23:15
 **/
public class TestImportAnnotation {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(ColorConfiguration.class);
		String[] beanDefinitions = context.getBeanDefinitionNames();
		Stream.of(beanDefinitions).forEach(System.out::println);
	}
}
