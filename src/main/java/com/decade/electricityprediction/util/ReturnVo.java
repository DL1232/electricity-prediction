package com.decade.electricityprediction.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一定义返回类
 *
 * @author lidongjie
 * @since 2020/3/20
 */
@Data
@ApiModel(description = "返回响应数据")
public class ReturnVo<T> implements Serializable {

    /**
     * 返回代码
     */
    @ApiModelProperty("返回状态码")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty("返回信息")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")

    private T data;

    /**
     * 构造器私有 只能通过静态方法构造对象
     *
     * @param code    状态码
     * @param message 提示信息
     * @param data    数据
     */
    private ReturnVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 默认的成功返回
     *
     * @param data 数据
     * @param <T>  data数据类型
     * @return 返回对象
     */
    public static <T> ReturnVo<T> success(T data) {
        return success(ReturnCode.SUCCESS, data);
    }

    /**
     * 使用 枚举类 的成功返回
     */
    public static <T> ReturnVo<T> success(ReturnCode returnCode, T data) {
        return new ReturnVo<>(returnCode.getCode(), returnCode.getMessage(), data);
    }

    /**
     * 错误返回
     *
     * @param returnCode 错误码枚举
     * @return 返回对象
     */
    public static <T> ReturnVo<T> failure(ReturnCode returnCode) {
        return new ReturnVo<>(returnCode.getCode(), returnCode.getMessage(), null);
    }
}
