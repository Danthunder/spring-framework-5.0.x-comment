package com.springlearn.springbean.bean_scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal Scope 实例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-19
 **/
public class ThreadLocalScope implements Scope {

	public static final String SCOPE_NAME = "thread-local";

	private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("thread-local-scope") {
		public Map<String, Object> initialValue() {
			return new HashMap<>();
		}
	};

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {

		// context 非空
		Map<String, Object> context = getContext();
		Object object = context.get(name);
		if (object == null) {
			object = objectFactory.getObject();
			context.put(name, object);
		}
		return object;
	}

	private Map<String, Object> getContext() {
		return threadLocal.get();
	}

	@Override
	public Object remove(String name) {
		// context 非空
		Map<String, Object> context = getContext();
		return context.remove(name);
	}

	// 销毁式的回调接口，帮助销毁或清除一些资源
	@Override
	public void registerDestructionCallback(String name, Runnable callback) {

	}

	// 通过 key 方式进行查询相应的接口
	@Override
	public Object resolveContextualObject(String key) {
		// context 非空
		Map<String, Object> context = getContext();
		return context.get(key);
	}

	@Override
	public String getConversationId() {
		Thread t = Thread.currentThread();
		return String.valueOf(t.getId());
	}
}
