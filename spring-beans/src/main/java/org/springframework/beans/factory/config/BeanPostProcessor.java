/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * BeanPostProcessor是spring框架提供的一个扩展类点（不止一个）。
 * 通过实现或者继承BeanPostProcessor接口，程序员就可插手bean实例化Instantiation
 * （不是初始化Initialization）前后的过程，从而减轻beanFactory的负担。注意，这个
 * 接口可以设置多个，会形成一个列表，然后依次执行。若想控制顺序，可以实现PriorityOrdered
 * 接口，重写getOrder()方法设置优先级数值来控制执行顺序。（但是spring内部的默认BeanPostProcessor
 * 通过手动添加的方式来执行）
 * 比如AOP就是在bean实例后期的时候将切面逻辑织入bean实例中的，AOP也正是通过BeanPostProcessor
 * 和IOC容器建立起了联系（由spring提供的默认BeanPostProcessor，spring提供了很多默认BeanPostProcessor）
 * spring提供的默认以下默认实现
 * 1. ApplicationContextAwareProcessor（acap）
 * 	  acap后置处理器的作用：当应用程序定义的Bean实现ApplicationContextAware接口时注入ApplicationContext
 * 	  对象。这是他的一个作用，还有其他作用，可以针对ApplicationContextAwareProcessor写例子
 * 2. InitDestroyAnnotationBeanPostProcessor
 * 	   用来处理自定义的初始化方法和销毁方法
 *     spring中提供3种自定义初始化和销毁方法，分别是
 *     1）通过@Bean指定init-method和destroy-method属性
 *     2）Bean实现InitializingBean接口和实现DisposableBean接口
 *     3）使用@PostConstruct、@PreDestroy注解
 *     通过这三种方法都能完成对bean生命周期的回调，关键在于InitDestroyAnnotationBeanPostProcessor
 * 3. InstantiationAwareBeanPostProcessor
 * 4. CommonAnnotationBeanPostProcessor
 * 5. AutowiredAnnotationBeanPostProcessor
 * 6. RequiredAnnotationBeanPostProcessor
 * 7. BeanValidationPostProcessor
 * 8. AbstractAutoProxyCreator
 * ......
 *
 *
 * Factory hook that allows for custom modification of new bean instances,
 * e.g. checking for marker interfaces or wrapping them with proxies.
 *
 * <p>ApplicationContexts can autodetect BeanPostProcessor beans in their
 * bean definitions and apply them to any beans subsequently created.
 * Plain bean factories allow for programmatic registration of post-processors,
 * applying to all beans created through this factory.
 *
 * <p>Typically, post-processors that populate beans via marker interfaces
 * or the like will implement {@link #postProcessBeforeInitialization},
 * while post-processors that wrap beans with proxies will normally
 * implement {@link #postProcessAfterInitialization}.
 *
 * @author Juergen Hoeller
 * @since 10.10.2003
 * @see InstantiationAwareBeanPostProcessor
 * @see DestructionAwareBeanPostProcessor
 * @see ConfigurableBeanFactory#addBeanPostProcessor
 * @see BeanFactoryPostProcessor
 */
public interface BeanPostProcessor {

	/**
	 * 在bean初始化之前执行
	 * Apply this BeanPostProcessor to the given new bean instance <i>before</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 */
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * Apply this BeanPostProcessor to the given new bean instance <i>after</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>In case of a FactoryBean, this callback will be invoked for both the FactoryBean
	 * instance and the objects created by the FactoryBean (as of Spring 2.0). The
	 * post-processor can decide whether to apply to either the FactoryBean or created
	 * objects or both through corresponding {@code bean instanceof FactoryBean} checks.
	 * <p>This callback will also be invoked after a short-circuiting triggered by a
	 * {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation} method,
	 * in contrast to all other BeanPostProcessor callbacks.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 * @see org.springframework.beans.factory.FactoryBean
	 */
	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
