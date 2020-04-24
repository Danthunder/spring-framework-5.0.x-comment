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

package org.springframework.context.annotation;

import java.util.function.Supplier;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Spring中用来处理@Bean定义的类主要有两个：
 * AnnotationConfigApplicationContext和
 * AnnotationConfigWebApplicationContext(是AnnotationConfigApplicationContext的Web版本)
 * 两者的用法以及对注解的处理方式几乎没有什么差别
 * 通过分析这个类，可以知道注册一个bean到spring容器有两种方法：
 * 1.直接将@Bean注解的javabean注册到spring容器中，参考public void register(Class<?>... annotatedClasses)
 *   但是直接把一个注解的bean注册到容器中也分两种方法
 *   1）在初始化Spring容器时注册并解析
 *   2）在容器创建之后，手动调用注册方法向容器注册，然后通过手动刷新容器，使得容器对注册的注解Bean进行注册并解析
 *   思考：为什么@Prifile要使用这类的第二种方法？
 *
 * 2. 通过扫描指定的包及其子包下的所有类
 * 扫描其实同上，也是两种方法，初始化的时候扫描，和初始化之后再扫描
 *
 * Standalone application context, accepting annotated classes as input - in particular
 * {@link Configuration @Configuration}-annotated classes, but also plain
 * {@link org.springframework.stereotype.Component @Component} types and JSR-330 compliant
 * classes using {@code javax.inject} annotations. Allows for registering classes one by
 * one using {@link #register(Class...)} as well as for classpath scanning using
 * {@link #scan(String...)}.
 *
 * <p>In case of multiple {@code @Configuration} classes, @{@link Bean} methods defined in
 * later classes will override those defined in earlier classes. This can be leveraged to
 * deliberately override certain bean definitions via an extra {@code @Configuration}
 * class.
 *
 * <p>See @{@link Configuration}'s javadoc for usage examples.
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 3.0
 * @see #register
 * @see #scan
 * @see AnnotatedBeanDefinitionReader
 * @see ClassPathBeanDefinitionScanner
 * @see org.springframework.context.support.GenericXmlApplicationContext
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

	/**
	 * 这个对象是一个reader，读取器
	 * 读取一个被加了注解的bean
	 * 这个对象在构造方法中实例化
	 */
	private final AnnotatedBeanDefinitionReader reader;

	/**
	 * 这个对象是一个扫描器，扫描所有加了注解的bean
	 * 同样在构造方法中实例化
	 */
	private final ClassPathBeanDefinitionScanner scanner;


	/**
	 * 初始化一个bean的读取器和扫描器
	 * 默认构造函数，如果直接调用这个默认构造方法，需要在稍后通过调用其register()
	 * 去注册配置类（java config）,并调用refresh()方法去刷新容器，
	 * 触发容器对注解Bean的载入、解析和注册过程
	 * Create a new AnnotationConfigApplicationContext that needs to be populated
	 * through {@link #register} calls and then manually {@linkplain #refresh refreshed}.
	 */
	public AnnotationConfigApplicationContext() {
		/*
		父类的构造方法
		创建一个读取注解的BeanDefinition读取器
		什么是Bean定义？BeanDefinition
		 */
		this.reader = new AnnotatedBeanDefinitionReader(this);

		/*
		可以用来扫描包或者类，继而转换成bd
		但是实际上扫描包工作不是scanner这个对象完成的
		是Spring自己new的一个ClassPathBeanDefinitionScanner，在方法
		org.springframework.context.annotation.ComponentScanAnnotationParser.parse
		中，创建的ClassPathBeanDefinitionScanner对象进行的Spring类扫描工作
		这里的scanner仅仅是为了程序员在外部调用AnnotationConfigApplicationContext对象
		的scan方法
		 */
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * Create a new AnnotationConfigApplicationContext with the given DefaultListableBeanFactory.
	 * @param beanFactory the DefaultListableBeanFactory instance to use for this context
	 */
	public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
		super(beanFactory);
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * 这个构造方法，传入一个被java config注解了的配置类，然后会把这个被注解了java config的类，
	 * 通过注解读取器读取后继而解析
	 * Create a new AnnotationConfigApplicationContext, deriving bean definitions
	 * from the given annotated classes and automatically refreshing the context.
	 * @param annotatedClasses one or more annotated classes,
	 * e.g. {@link Configuration @Configuration} classes
	 */
	public  AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {

		/* this()
		由于他有父类，会先调用父类的构造方法（无参的），然后才会调用自己的构造方法，
		在自己的构造方法中初始化一个读取器和扫描器，因此执行完这个构造方法后，初始化了三个对象
		1. Bean工厂：
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		2. 外部使用的BeanDefinition动态读取器：（外部传来的beanDefinition，没有@Component等注解（未被Spring扫描）。可通过该读取器获取beanDefinition）
			AnnotatedBeanDefinitionReader reader =  new AnnotatedBeanDefinitionReader(this);
		3. 外部使用的包或者类的扫描器（外部传来的包或类，没有@Component等注解（未被Spring扫描），可通过该扫描器扫描包或类）。例如MyBatis中的
			提供的注解@MapperScan("多个包路径")，让spring对指定的类路径进行扫描。MapperScan源码中引入@Import(MapperScannerRegistrar.class)
			这个类MapperScannerRegistrar中有对象ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
			这个类ClassPathMapperScanner就是继承了org.springframework.context.annotation.ClassPathBeanDefinitionScanner，实现
			了自定义逻辑的类扫描。原因在于spring不能扫描一个接口！而Mybatis为了实现对DAO接口的扫描，对ClassPathBeanDefinitionScanner进行
			了扩展。
			总结，可以使用这个ClassPathBeanDefinitionScanner类的对象完成类的扫描（spring的扫描规则），也可以扩展这个对象，实现自定义规则的类扫描
			ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(this);
		 */
		this();

		/*register(annotatedClasses):
		1. 把配置类注册给spring（注册annotatedClasses类），即将annotatedClasses和spring内部的一些类（例如
			org.springframework.context.annotation.internalConfigurationAnnotationProcessor、
			org.springframework.context.event.internalEventListenerFactory、
			org.springframework.context.event.internalEventListenerProcessor、
			org.springframework.context.annotation.internalAutowiredAnnotationProcessor、
			org.springframework.context.annotation.internalCommonAnnotationProcessor、
			org.springframework.context.annotation.internalRequiredAnnotationProcessor
			这些类是spring内部的、已知的、不需要通过spring扫描获取）其加入到beanDefinitionMap中，因为这些类不要
		2. spring扫描beanDefinition是在refresh()中invokeBeanFactoryPostProcessors(beanFactory)这个方法，
			该方法会执行程序员和spring内部提供的BeanFactoryPostProcessors，在这些BeanFactoryPostProcessors
			中完成扫描。注意只是对指定路径下、符合规范的class进行扫描。但是这些配置类annotatedClasses也会成为
			beanDefinition（最终成为spring中的bean），需要手动注册给	spring！
			任何指定路径下的class，通过扫描得到其beanDefinition；而指定哪些路径下的类进行扫描是通过Appconfig所代表
			的beanDefinition获取！而Appconfig所代表的beanDefinition如何得到？如果还需要通过扫描，则这个过程变为无
			限循环的状态，因此必须通过手动注册给spring得到，因此register(annotatedClasses)的作用就是将初始配置信息
			注册给spring，变成beanDefinition，放到beanDefinitionMap中
		 */
		register(annotatedClasses);


		/*
		1. refresh()方法，而不是 init() 这种名字的方法。因为 ApplicationContext
			建立起来以后，是可以通过调用 refresh() 这个方法重建的，refresh() 会将原
			来的 ApplicationContext 销毁，然后再重新执行一次初始化操作。
		 */
		refresh();
	}

	/**
	 * Create a new AnnotationConfigApplicationContext, scanning for bean definitions
	 * in the given packages and automatically refreshing the context.
	 * @param basePackages the packages to check for annotated classes
	 */
	public AnnotationConfigApplicationContext(String... basePackages) {
		this();
		scan(basePackages);
		refresh();
	}


	/**
	 * Propagates the given custom {@code Environment} to the underlying
	 * {@link AnnotatedBeanDefinitionReader} and {@link ClassPathBeanDefinitionScanner}.
	 */
	@Override
	public void setEnvironment(ConfigurableEnvironment environment) {
		super.setEnvironment(environment);
		this.reader.setEnvironment(environment);
		this.scanner.setEnvironment(environment);
	}

	/**
	 * Provide a custom {@link BeanNameGenerator} for use with {@link AnnotatedBeanDefinitionReader}
	 * and/or {@link ClassPathBeanDefinitionScanner}, if any.
	 * <p>Default is {@link org.springframework.context.annotation.AnnotationBeanNameGenerator}.
	 * <p>Any call to this method must occur prior to calls to {@link #register(Class...)}
	 * and/or {@link #scan(String...)}.
	 * @see AnnotatedBeanDefinitionReader#setBeanNameGenerator
	 * @see ClassPathBeanDefinitionScanner#setBeanNameGenerator
	 */
	public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
		this.reader.setBeanNameGenerator(beanNameGenerator);
		this.scanner.setBeanNameGenerator(beanNameGenerator);
		getBeanFactory().registerSingleton(
				AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR, beanNameGenerator);
	}

	/**
	 * Set the {@link ScopeMetadataResolver} to use for detected bean classes.
	 * <p>The default is an {@link AnnotationScopeMetadataResolver}.
	 * <p>Any call to this method must occur prior to calls to {@link #register(Class...)}
	 * and/or {@link #scan(String...)}.
	 */
	public void setScopeMetadataResolver(ScopeMetadataResolver scopeMetadataResolver) {
		this.reader.setScopeMetadataResolver(scopeMetadataResolver);
		this.scanner.setScopeMetadataResolver(scopeMetadataResolver);
	}


	//---------------------------------------------------------------------
	// Implementation of AnnotationConfigRegistry
	//---------------------------------------------------------------------

	/**
	 * 注册单个java bean给容器
	 * 当有新的类时，可以用这个方法
	 * 注意：注册之后需要手动调用refresh()方法去刷新容器
	 *
	 * 他可以注册一个配置类（@Configuration）
	 * 也可以注册一个bean
	 *
	 * Register one or more annotated classes to be processed.
	 * <p>Note that {@link #refresh()} must be called in order for the context
	 * to fully process the new classes.
	 * @param annotatedClasses one or more annotated classes,
	 * e.g. {@link Configuration @Configuration} classes
	 * @see #scan(String...)
	 * @see #refresh()
	 */
	public void register(Class<?>... annotatedClasses) {
		Assert.notEmpty(annotatedClasses, "At least one annotated class must be specified");
		this.reader.register(annotatedClasses);
	}

	/**
	 * Perform a scan within the specified base packages.
	 * <p>Note that {@link #refresh()} must be called in order for the context
	 * to fully process the new classes.
	 * @param basePackages the packages to check for annotated classes
	 * @see #register(Class...)
	 * @see #refresh()
	 */
	public void scan(String... basePackages) {
		Assert.notEmpty(basePackages, "At least one base package must be specified");
		this.scanner.scan(basePackages);
	}


	//---------------------------------------------------------------------
	// Convenient methods for registering individual beans
	//---------------------------------------------------------------------

	/**
	 * Register a bean from the given bean class, deriving its metadata from
	 * class-declared annotations, and optionally providing explicit constructor
	 * arguments for consideration in the autowiring process.
	 * <p>The bean name will be generated according to annotated component rules.
	 * @param annotatedClass the class of the bean
	 * @param constructorArguments argument values to be fed into Spring's
	 * constructor resolution algorithm, resolving either all arguments or just
	 * specific ones, with the rest to be resolved through regular autowiring
	 * (may be {@code null} or empty)
	 * @since 5.0
	 */
	public <T> void registerBean(Class<T> annotatedClass, Object... constructorArguments) {
		registerBean(null, annotatedClass, constructorArguments);
	}

	/**
	 * Register a bean from the given bean class, deriving its metadata from
	 * class-declared annotations, and optionally providing explicit constructor
	 * arguments for consideration in the autowiring process.
	 * @param beanName the name of the bean (may be {@code null})
	 * @param annotatedClass the class of the bean
	 * @param constructorArguments argument values to be fed into Spring's
	 * constructor resolution algorithm, resolving either all arguments or just
	 * specific ones, with the rest to be resolved through regular autowiring
	 * (may be {@code null} or empty)
	 * @since 5.0
	 */
	public <T> void registerBean(@Nullable String beanName, Class<T> annotatedClass, Object... constructorArguments) {
		this.reader.doRegisterBean(annotatedClass, null, beanName, null,
				bd -> {
					for (Object arg : constructorArguments) {
						bd.getConstructorArgumentValues().addGenericArgumentValue(arg);
					}
				});
	}

	@Override
	public <T> void registerBean(@Nullable String beanName, Class<T> beanClass, @Nullable Supplier<T> supplier,
			BeanDefinitionCustomizer... customizers) {

		this.reader.doRegisterBean(beanClass, supplier, beanName, null, customizers);
	}

}
