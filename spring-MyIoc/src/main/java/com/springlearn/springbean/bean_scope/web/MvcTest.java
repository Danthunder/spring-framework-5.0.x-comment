package com.springlearn.springbean.bean_scope.web;

import com.springlearn.iocoverview.domain.User;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

/**
 *
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-18
 **/
@Configuration
@ComponentScan("com.springlearn.springbean.bean_scope")
public class MvcTest {

	@Bean
//	@RequestScope
	@SessionScope
	public User user() {
		User user = new User();
		user.setName("wangdanning");
		user.setId(System.nanoTime());
		return user;
	}

	public static void main(String[] args) throws LifecycleException {
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(MvcTest.class);
		ac.refresh();

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(9090);

		File base = new File(System.getProperty("java.io.tmpdir"));
		/**
		 * tomcat.addWebapp()表示这是一个web项目
		 * contextPath: Tomcat的访问路径
		 * docBase: 项目的Web目录，即访问项目主页时，找该目录下的index.html
		 * 所以这里不能用addWebapp（spring-boot也没有用这个）
		 * 因为一旦使用addWebapp，当Tomcat加载当前项目时就会认为该项目是Web
		 * 项目，利用 Servlet3.0 特性会去调用钩子onStartup方法，但启动项目
		 * 会报异常java.lang.ClassNotFoundException: org.apache.jasper.servlet.JspServlet
		 * （spring-boot 1.x中默认不支持JSP），需要依赖tomcat-embed-jasper
		 * jar包，才能解决该异常
		 */
//		Context ctx = tomcat.addWebapp(
//				"/",
//				base.getAbsolutePath());
		Context ctx = tomcat.addContext(
				"/",
				base.getAbsolutePath());

		// Create and register the DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(ac);
		Wrapper wrapper = Tomcat.addServlet(ctx, "SpringMvc", servlet);
		ctx.addServletMappingDecoded("/", "SpringMvc");
		// 代表tomcat一启动就调用DispatcherServlet#init方法
		// 初始化请求和controller的映射
		wrapper.setLoadOnStartup(1);
//		wrapper.addMapping("/*");

		tomcat.start();
		tomcat.getServer().await();
	}
}
