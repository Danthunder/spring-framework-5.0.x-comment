package com.spring.importAnnotation.config;

import com.spring.importAnnotation.entity.Yellow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wang danning
 * @since 2020-03-20 23:21
 **/
@Configuration
public class ColorRegistrarConfiguration {

	@Bean
	public Yellow yellow() {
		return new Yellow();
	}
}
