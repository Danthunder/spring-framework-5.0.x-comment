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

package org.springframework.web.context.request;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.lang.Nullable;

/**
 * Session-backed {@link org.springframework.beans.factory.config.Scope}
 * implementation.
 *
 * <p>Relies on a thread-bound {@link RequestAttributes} instance, which
 * can be exported through {@link RequestContextListener},
 * {@link org.springframework.web.filter.RequestContextFilter} or
 * {@link org.springframework.web.servlet.DispatcherServlet}.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @since 2.0
 * @see RequestContextHolder#currentRequestAttributes()
 * @see RequestAttributes#SCOPE_SESSION
 * @see RequestContextListener
 * @see org.springframework.web.filter.RequestContextFilter
 * @see org.springframework.web.servlet.DispatcherServlet
 */
public class SessionScope extends AbstractRequestAttributesScope {

	@Override
	protected int getScope() {
		return RequestAttributes.SCOPE_SESSION;
	}

	@Override
	public String getConversationId() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		/**
		 * 这里加锁的原因：确保多线程执行的时候，只有一个线程互斥的操作，这是和 Request 的区别
		 * Http 会话是跟着用户走的，通常情况下不会出现线程的安全性问题。但是可能出现这种情况，即
		 * 一个用户开多个浏览器，同时在浏览器中开多个 Tab 小窗口，小窗口是共用同一个 session，即
		 * 共用一份 cookie。为了保证此种情况，操作的顺序性，防止前后数据的不一致，在这里加了把锁。
		 * Request 是每个线程对应一个 request，线程之间的作用域并不会共享，天然的分隔开了。
		 *
		 *
		 */
		Object mutex = RequestContextHolder.currentRequestAttributes().getSessionMutex();
		synchronized (mutex) {
			return super.get(name, objectFactory);
		}
	}

	@Override
	@Nullable
	public Object remove(String name) {
		Object mutex = RequestContextHolder.currentRequestAttributes().getSessionMutex();
		synchronized (mutex) {
			return super.remove(name);
		}
	}

}
