package com.spring.importAnnotation.config;

import com.spring.importAnnotation.entity.Red;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Wang danning
 * @since 2020-03-20 23:12
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({Red.class,
		ColorRegistrarConfiguration.class,
		ColorImportSelector.class,
		ColorImportBeanDefinitionRegistrar.class})
public @interface EnableColor {
}
