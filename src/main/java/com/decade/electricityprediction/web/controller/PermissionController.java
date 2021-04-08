package com.decade.electricityprediction.web.controller;


import com.decade.electricityprediction.persistence.entity.PermissionEntity;
import com.decade.electricityprediction.service.PermissionService;
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
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permission")
    public ReturnVo<List<PermissionEntity>> listAll() {
        List<PermissionEntity> permissionEntities = permissionService.listAll();
        return ReturnVo.success(permissionEntities);
    }

    @GetMapping("/permission/role/{id}")
    public ReturnVo<List<PermissionEntity>> listPermissionByRoleId(
            @PathVariable("id") Long roleId) {
        List<PermissionEntity> permissionEntities = permissionService.listPermissionByRoleId(roleId);
        return ReturnVo.success(permissionEntities);
    }

}

