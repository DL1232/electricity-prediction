package com.decade.electricityprediction.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一定义返回类
 *
 * @author lidongjie
 * @since 2020/3/20
 */
@Data
public class ReturnVo<T> implements Serializable {

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private ReturnVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ReturnVo<T> success(T data) {
        return new ReturnVo<>(200, "ok", data);
    }

    public static <T> ReturnVo<T> failure(Integer code, String message, T data) {
        return new ReturnVo<>(code, message, data);
    }

    public static <T> ReturnVo<T> failure(ReturnCode returnCode, T data) {
        return failure(returnCode.getCode(), returnCode.getMessage(), data);
    }
}
