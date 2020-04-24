package com.springlearn.jdkdynamic;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @program: spring.com.springlearn.jdkdynamic.ProxyUtil
 * @description: 手动模拟JDK动态代理
 * @author: Wang danning
 * @create: 2020-01-21 19:15
 * @version: 1.0
 **/
public class ProxyUtil {
	/**
	 * java content--->String
	 * .java ---> 编译
	 * .class
	 * .new <--- 反射 <--- classloader(从lib\从工程里面\从外部资源)
	 *
	 * target: 目标对象
	 * @return
	 */
	public static Object newInstance(Class targetInterface, CustomInvocationHander h) {
		/*
		package com.springlearn.jdkdynamic;
		public class LogEnhance implements UserDao{
			private UserDao target;
			public LogEnhance(UserDao target) {
				this.target = target;
			}
			@Override
			public void query() {
				System.out.println("log");
				target.query();
			}
		}
		 */
		// 目标对象的接口
//		Class targetInterface = target.getClass().getInterfaces()[0];
		String line = "\n";
		String tab = "\t";
		String content;
		String proxyClassName = "$Proxy";
		String targetInterfaceName = targetInterface.getSimpleName();
		Method[] declaredMethods = targetInterface.getDeclaredMethods();

		String packageContent = "package com.google;" + line;
		String importContent = "import " + targetInterface.getName() + ";" + line
				+ "import java.lang.reflect.Method;" + line
				+ "import com.springlearn.jdkdynamic.CustomInvocationHander;" + line;


		String clazzFirstLineContent = "public class " + proxyClassName + " implements "
				+ targetInterfaceName + "{" + line;
//		String fieldContent = tab + "private " + targetInterfaceName + " target;" + line;
		String fieldContent = tab + "private CustomInvocationHander h;" + line;
//		String constructContent = tab + "public " + proxyClassName + "(" + targetInterfaceName
//				+ " target){" + line
//				+ tab + tab + "this.target = target;" + line
//				+ tab + "}" + line;
		String constructContent = tab + "public " + proxyClassName + "(CustomInvocationHander h) {" + line
				+ tab + tab + "this.h = h;" + line
				+ tab + "}" + line;
		String methodsContent = "";


		for (Method method : declaredMethods) {
			String methodReturnType = method.getReturnType().getSimpleName();
			String methodName = method.getName();
			// 处理该方法的参数 String.class, String.class
			Class[] args = method.getParameterTypes();
			String argsContent = "";
			String paramsContent = "";
			String argsTypeContent = "";
			int flag = 0;
			for (Class arg : args) {
				String argType = arg.getSimpleName();
				// 示例：String p0,String p1,
				argsContent += argType + " p" + flag + ",";
				paramsContent += "p" + flag + ",";
				argsTypeContent += "," + argType + ".class";
				flag ++;
			}
			if (argsContent.length() > 0) {
//				paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(","));
				paramsContent = "," + paramsContent.substring(0, paramsContent.lastIndexOf(","));
				argsContent = argsContent.substring(0, argsContent.lastIndexOf(","));
			}

			// 判断是否需要return，如果不是void类型，则需要添加return关键字
			String invokeMethodContent = tab + tab + "Method method = null;" + line
					+ tab + tab + "try {" + line
			  		+ tab + tab + tab + "method = Class.forName(\"" +
						targetInterface.getName() + "\").getDeclaredMethod(\"" +
						methodName + "\"" + argsTypeContent + ");" + line
					+ tab + tab + "} catch (Exception e) {}" + line;

			String needReturn = "void".equals(methodReturnType) ? "" : "return " + "(" + methodReturnType + ")";
			methodsContent += tab + "public " + methodReturnType + " " + methodName + "(" + argsContent + ") {" + line
//					+ tab + tab + "System.out.println(\"log\");" + line  // 静态写死
					+ invokeMethodContent + line
					+ tab + tab + needReturn + "h.invoke(method" + paramsContent + ");" + line
//					+ tab + tab + needReturn + " target." + methodName + "(" + paramsContent + ");" + line
					+ tab + "}" + line;

		}
//		content = packageContent + importContent + clazzFirstLineContent + fieldContent
//				+ constructContent + methodsContent + "}";
		content = packageContent + importContent + clazzFirstLineContent + fieldContent
				+ constructContent + methodsContent + "}";


		File file = new File("D:\\com\\google\\$Proxy.java");

		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter("D:\\com\\google\\$Proxy.java");
			fileWriter.write(content);
			fileWriter.flush();
			fileWriter.close();

			// 利用第三方工具将Java文件动态编译为class文件
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
			Iterable unit = standardJavaFileManager.getJavaFileObjects(file);

			JavaCompiler.CompilationTask t = javaCompiler.getTask(null, standardJavaFileManager, null, null, null, unit);
			t.call();
			standardJavaFileManager.close();

			// 严重注意：这里指定到全限定类名（com.google.$Proxy）的父路径即可，这里即d:\\即可
			// 不能指定到com.google的$Proxy.class文件，因为需要保证包名完整信息
			URL[] urls = new URL[]{new URL("file:d:\\\\")};
			URLClassLoader urlClassLoader = new URLClassLoader(urls);
			Class clazz = urlClassLoader.loadClass("com.google.$Proxy");

			// 获取构造方法，利用构造方法创建该动态生成的代理对象proxyObject
			Constructor constructor = clazz.getDeclaredConstructor(CustomInvocationHander.class);
			return constructor.newInstance(h);

		}catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}
}
