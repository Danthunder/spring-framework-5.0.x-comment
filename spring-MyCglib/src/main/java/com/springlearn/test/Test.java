package com.springlearn.test;

import com.springlearn.app.Appconfig;
import com.springlearn.dao.CityDao;
import com.springlearn.dao.MyDao;
import com.springlearn.service.City;
import com.springlearn.service.CityService;
import com.springlearn.service.TestService;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: spring.com.springlearn.test.Test
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-05 12:24
 * @version: 1.0
 **/
public class Test {
	public static void main(String... args) {

		/*
		1. 什么叫spring容器，即所有实现或继承了ApplicationContext的类可以称为spring容器
		2. 代码级别层面，即AnnotationConfigApplicationContext、ClassPathXmlApplicationContext、AnnotationConfigWebApplicationContext
		（web项目的容器）等，包含spring各种组件的集合，成为spring上下文、容器、环境。组件例如：
			1) ClassPathBeanDefinitionScanner组件:扫描路径下的所有符合规范的类组件
			2) BeanDefinitionRegistry组件:提供访问beanDefinitionMap(DefaultListableBeanFactory类，map里面保存bean名称和bean描述)的组件
			3) AnnotatedBeanDefinitionReader组件:将符合规范的类加工成beanDefinition
			4) BeanFactory（Bean工厂）组件:即实现类DefaultListableBeanFactory，将符合规范的类加工成beanDefinition，
			5) spring单例池，ConcurrentHashMap<String, object> singletonObjects（DefaultSingletonBeanRegistry类中）
			6) spring各种工厂后置处理器，例如BeanFactoryPostProcessor等
			7) 各种BeanPostProcessor
			8) ImportBeanDefinitionRegistrar:把bean注入到spring容器不止有@Service、@Component等注解方式，还可以通过实现
				ImportBeanDefinitionRegistrar接口，将自己写的代码封装成BeanDefinition对象。实现此接口的类会回调
				postProcessBeanDefinitionRegistry方法，注册到spring容器中。
			......
		 */

		/*
		1. java object和spring bean的区别：一个java object，只有经历了完整的spring生命周期后（即被spring各个组件的方法执行过后，
		产生的对象）就是一个spring bean。
		2. java中产生对象的方式：new、反射、反序列化、clone
		3. 当spring扫描符合规范Class类（有@Component等注解）时，并没有直接new出来该对象，而是创建了一个beanDefinition对象
			（GenericBeanDefinition实现类），并将该类的元数据信息（包括Class:实例bean、Name:BeanName、Scope:bean作用域、
			Constructor arguments、Properties、Autowiring mode、Lazy initialization mode、Initialization method、
			Destruction method等）存储在beanDefinition里面。注意，beanDefinition是用来描述bean的！
		4. 这些BeanDefinition objects存放在spring容器的BeanFactory（即实现类DefaultListableBeanFactory）中的ConcurrentHashMap
			(即beanDefinitionMap，key是bean name)
		5. 判断是否存在各种BeanFactoryPostProcessor，例如BeanFactoryPostProcessor(List)、ImportBeanDefinitionRegistrar
			1) 如果没有，则执行preInstantiateSingletons()，创建bean对象
			2) 如果有，则执行这些BeanFactoryProcessor，对beanDefinition处理更新(通过GenericBeanDefinition对象，改变某个类对应的
			beanDefinition的属性，例如beanClassName)后，执行preInstantiateSingletons()，创建bean对象。该过程的使用示例请看实现类
			MyBeanFactoryPostProcessor
			a) 由2)点引申，spring中实例化某个bean跟这个类Class没有关系，是跟这个类被加工成的beanDefinition有很大关系！


		 */

		/*
		Spring的bean的初始化过程（生命周期）


		 */

//        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
//        genericBeanDefinition.setBeanClassName("cityService");
//        genericBeanDefinition.setScope("singleton");
//        genericBeanDefinition.setBeanClass(CityService.class);
//        genericBeanDefinition.setLazyInit(false);


        /*

        这个是正常流程：
        Class文件经过classloader加载器后，形成了在内存的klass对象，经过spring加载后，形成
        beanDefinition后，put到一个beanDefinitionMap中，通过preInstantiateSingletons
        创建该对象（new object，这个过程很复杂），放到spring单例池（如果该类是默认单例情况下）

         */

		// ....

        /*
         0. 检查Appconfig类中的注解@ComponentScan("com")，在路径com下scan
         1. 通过for循环，依次把带有注解@Component、@Repository、@Service、@Controller的类拿到
            for {
                1. 解析该类的名字
                2. 创建GenericBeanDefinition对象，这个genericBeanDefinition对象（其父类是BeanDefinition）
                    GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
                3. genericBeanDefinition.setBeanClassName("testBean");设置类名
                   genericBeanDefinition.setScope("singleton");设置作用域
                   genericBeanDefinition.setBeanClass(TestBean.class);设置类型
                   genericBeanDefinition.setLazyInit(false);设置是否懒加载
                   ......将该类的信息封装到GenericBeanDefinition对象中
                4. 将GenericBeanDefinition对象加入到beanDefinitionMap中
                    beanDefinitionMap.put("testBean", genericBeanDefinition)

            }
         2. 再通过一个for循环，创建实例
            for {
                genericBeanDefinition.getBeanClass().newInstance();
            }
          */

        // Appconfig也会处理为一个bean，这个bean是手动注册给spring
		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext(Appconfig.class);
		Appconfig a = annotationConfigApplicationContext.getBean(Appconfig.class);
// 		System.out.println(a);

//		Enhancer enhancer = new Enhancer();
//		// 增强父类，Cglib是基于继承来的，所以需要设置父类，要代理哪个类，就把其作为父类
//		enhancer.setSuperclass(CityDao.class);
//		enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);

		// 过滤方法，不能每次都去new
//		enhancer.setCallbackFilter(CALLBACK_FILTER);
//		enhancer.setCallback(new MyMethodInterceptor());
//		CityDao cityDao = (CityDao) enhancer.create();
//		cityDao.query();




//		City cityService = (City) annotationConfigApplicationContext.getBean("cityService");
//		try {
//			cityService.testFunc("My");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// CGLIB
//		MyDao bean = annotationConfigApplicationContext.getBean(MyDao.class);
//		bean.query();

//		AnnotationConfig
//		ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("spring.xml");
//		cac.getBean("cityService");

//		annotationConfigApplicationContext.getBean("cityService");
//		System.out.println(annotationConfigApplicationContext.getBean(CityService.class));
//		System.out.println(annotationConfigApplicationContext.getBean(TestService.class));


	}
}
