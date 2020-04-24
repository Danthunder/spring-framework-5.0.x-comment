package com.spring.importAnnotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Wang danning
 * @since 2020-03-20 23:24
 **/
public class ColorImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.spring.importAnnotation.entity.Blue",
		"com.spring.importAnnotation.entity.Green"};
	}
}
