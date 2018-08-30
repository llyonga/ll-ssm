package com.io.ssm.framework.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @description: JSON和Bean对象之间互转工具类
 * @author: llyong
 * @date: 2018/8/30
 * @time: 23:40
 * @version: 1.0
 */
public class JSONUtil {

    /**
     * JOSN字符串转Bean
     * @param classType Bean类型
     * @param json  JSON字符串
     * @param <T>
     * @return
     */
    public static <T> T toBean(Class<T> classType,String json) {
        return JSON.parseObject(json, classType);
    }

    /**
     * JOSN字符串转BeanList
     * @param classType Bean类型
     * @param json  JSON字符串
     * @param <T>
     * @return
     */
    public static <T> List<T> toBeanList(Class<T> classType,String json) {
        return JSON.parseArray(json,classType);
    }

    /**
     * Bean转JSON字符串
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }
}
