package com.decade.electricityprediction.util;

/**
 * @author lidongjie
 * @since 2021/3/20
 */
public enum ReturnCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 400 用户名或密码错误
     */
    LOGIN_FAILURE(400, "用户名或密码错误"),

    /**
     * 403 Forbidden 没有权限
     */
    FORBIDDEN(403, "没有访问权限");

    ReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
