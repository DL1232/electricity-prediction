package com.decade.electricityprediction.util;

/**
 * @author lidongjie
 * @since 2021/3/20
 */
public enum ReturnCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "ok"),

    /**
     * 操作失败
     */
    FAIL(400, "fail"),

    /**
     * 空指针异常
     */
    NullPointerException(400, "NPE_MSG"),

    /**
     * 自定义异常之返回值为空
     */
    NullResponseException(400, "NRE_MSG");

    ReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    Integer code;
    String message;

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
