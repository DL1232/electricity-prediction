package com.decade.electricityprediction.web.controller;


import com.decade.electricityprediction.persistence.entity.UserEntity;
import com.decade.electricityprediction.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/listAll")
    public List<UserEntity> listAll() {
        return userService.findAll();
    }

}

