package com.springlearn.springbean.dependency_injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入的注解
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-14
 **/
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
