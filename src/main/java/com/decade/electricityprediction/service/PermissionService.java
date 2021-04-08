package com.decade.electricityprediction.service;

import com.decade.electricityprediction.persistence.entity.PermissionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lidongjie
 * @since 2021-03-19
 */
public interface PermissionService extends IService<PermissionEntity> {

    List<PermissionEntity> listAll();

    List<PermissionEntity> listPermissionByRoleId(Long roleId);
}
