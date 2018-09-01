package com.io.ssm.framework.aspect;

import com.io.ssm.framework.annotations.DataSource;
import com.io.ssm.framework.datasource.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:  数据源切换的切面类
 * @author: llyong
 * @date: 2018/9/1
 * @time: 19:41
 * @version: 1.0
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 消息前置通知
     * 用于拦截service的方法，注入数据源
     * @param point
     */
    @Before("@annotation(com.io.ssm.framework.annotations.DataSource)")
    public void doBefore(JoinPoint point) {
        try {
            Object target = point.getTarget();
            String methodName = point.getSignature().getName();

            Class<? extends Object> targetClass = target.getClass();
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = targetClass.getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = method.getAnnotation(DataSource.class);
                DataSourceHolder.setDataSourceType(dataSource.value());
            }
        } catch (Exception e) {
            LOGGER.error("DataSourceAspect before error",e);
        }
    }
}
