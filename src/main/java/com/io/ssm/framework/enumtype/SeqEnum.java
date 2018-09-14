package com.io.ssm.framework.enumtype;

/**
 * @description:
 * @author: llyong
 * @date: 2018/9/14
 * @time: 12:27
 * @version: 1.0
 */
public enum SeqEnum {
    MENU("menu","菜单序列"),
    USER("user","用户序列"),
    ;

    private String code;
    private String text;

    SeqEnum(String code) {
        this.code = code;
    }

    SeqEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
