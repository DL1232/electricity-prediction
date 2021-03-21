package com.decade.electricityprediction.web.controller;


import com.decade.electricityprediction.persistence.entity.UserEntity;
import com.decade.electricityprediction.service.UserService;
import com.decade.electricityprediction.util.ReturnCode;
import com.decade.electricityprediction.util.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lidongjie
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @ApiOperation(value = "查询所有用户")
    public ReturnVo<List<UserEntity>> listAll() {
        List<UserEntity> userList = userService.findAll();
        return ReturnVo.success(userList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @ApiOperation(value = "查询指定 id 用户")
    public ReturnVo<UserEntity> listById(@PathVariable String id) {
        Integer integer;
        try {
            integer = new Integer(id);
        } catch (Exception e) {
            return ReturnVo.failure(ReturnCode.ILLEGAL_FORMAT);
        }
        UserEntity user = userService.findById(integer);
        return ReturnVo.success(user);
    }

}

