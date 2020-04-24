/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.transaction.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.transaction.config.TransactionManagementConfigUtils;

/**
 * Selects which implementation of {@link AbstractTransactionManagementConfiguration}
 * should be used based on the value of {@link EnableTransactionManagement#mode} on the
 * importing {@code @Configuration} class.
 *
 * @author Chris Beams
 * @since 3.1
 * @see EnableTransactionManagement
 * @see ProxyTransactionManagementConfiguration
 * @see TransactionManagementConfigUtils#TRANSACTION_ASPECT_CONFIGURATION_CLASS_NAME
 */
public class TransactionManagementConfigurationSelector extends AdviceModeImportSelector<EnableTransactionManagement> {

	/**
	 * Returns {@link ProxyTransactionManagementConfiguration} or
	 * {@code AspectJTransactionManagementConfiguration} for {@code PROXY}
	 * and {@code ASPECTJ} values of {@link EnableTransactionManagement#mode()},
	 * respectively.
	 *
	 * PROXY代理模式：载入了 AutoProxyRegistrar、ProxyTransactionManagementConfiguration 2个类
	 *
	 * 1. ProxyTransactionManagementConfiguration：配置类，定义了事务增强器。通过@Bean的方式向Spring容器注入
	 * 	1）. BeanFactoryTransactionAttributeSourceAdvisor：事务切面增强器，包括了事务通知切面和切入点
	 * 	2）. TransactionAttributeSource：解析事务属性，缓存事务属性，提供获取事务属性的API(getTransactionAttribute)
	 * 	3）. TransactionInterceptor：事务的通知切面，当执行事务时就是执行其中的 invoke 方法
	 *
	 * 2. AutoProxyRegistrar：给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件；
	 * 		该组件是一个后置通知Bean，根据上面的信息给业务Bean生成代理Bean（代理对象）。代理对象
	 * 		执行方法利用拦截器链进行调用
	 */
	@Override
	protected String[] selectImports(AdviceMode adviceMode) {
		switch (adviceMode) {
			case PROXY:
				return new String[] {AutoProxyRegistrar.class.getName(),
						ProxyTransactionManagementConfiguration.class.getName()};
			case ASPECTJ:
				return new String[] {
						TransactionManagementConfigUtils.TRANSACTION_ASPECT_CONFIGURATION_CLASS_NAME};
			default:
				return null;
		}
	}

}
