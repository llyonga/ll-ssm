package com.io.ssm.framework.annotations;

import java.lang.annotation.*;

/**
 * @description: 数据源切换
 * @author: llyong
 * @date: 2018/9/1
 * @time: 19:35
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    public static final String MASTER = "MASTER";
    public static final String SLAVE = "SLAVE";

    String value() default MASTER;
}
