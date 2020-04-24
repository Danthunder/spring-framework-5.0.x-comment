package com.spring.aop.app;

import com.spring.aop.anno.EnableMyRedis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: spring.Appconfig
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 00:46
 * @version: 1.0
 **/
@Configuration
@ComponentScan("com.spring.aop")
//@EnableMyRedis(keyValue = "redis",aliveTime = "1000")
@EnableAspectJAutoProxy
//@MapperScan
//@EnableRedissonHttpSession
public class Appconfig {
}
