package com.io.ssm.framework.datasource;

import org.springframework.util.Assert;

/**
 * @description: 设置数据源
 * @author: llyong
 * @date: 2018/9/1
 * @time: 19:53
 * @version: 1.0
 */
public class DataSourceHolder {

    /**
     * 由于存在多线程并发，所以选用ThreadLocal，并将当前使用的数据源放入一个共享变量中
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 将数据源放入一个ThreadLocal中
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        Assert.notNull(dataSourceType,"the data 'dataSourceType' cannot be null");
        contextHolder.set(dataSourceType);
    }

    /**
     * ThreadLocal中获取数据源
     * @return
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 移除
     */
    public static void removeDataSourceType() {
        contextHolder.remove();
    }
}
