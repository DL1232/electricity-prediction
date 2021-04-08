package com.decade.electricityprediction.web.controller;


import com.decade.electricityprediction.persistence.entity.PermissionEntity;
import com.decade.electricityprediction.persistence.entity.RoleEntity;
import com.decade.electricityprediction.service.RoleService;
import com.decade.electricityprediction.util.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public ReturnVo<List<RoleEntity>> listAll() {
        List<RoleEntity> list = roleService.list();
        return ReturnVo.success(list);
    }
}

