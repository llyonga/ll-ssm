package com.io.ssm.framework.enumtype;

/**
 * @description:  主从DB枚举类
 * @author: llyong
 * @date: 2018/9/6
 * @time: 21:47
 * @version: 1.0
 */
public enum DataSourceEnum {

    MASTER("MASTER"),
    SLAVE("SLAVE"),
    ;

    private String text;

    DataSourceEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setType(String text) {
        this.text = text;
    }
}
