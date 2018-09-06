package com.io.ssm.module.web.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;

/**
 * @description:
 * @author: llyong
 * @date: 2018/9/1
 * @time: 22:39
 * @version: 1.0
 */
//@Controller
public class RedisTestController {

//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    public void fun() {
//
//    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-redis.xml");
        RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set("lly_name","zhangsan");
        Object name = opsForValue.get("lly_name");
        System.out.println(name);
    }
}
