package com.decade.electricityprediction.service.impl;

import com.decade.electricityprediction.persistence.entity.PermissionEntity;
import com.decade.electricityprediction.persistence.mapper.PermissionMapper;
import com.decade.electricityprediction.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lidongjie
 * @since 2021-03-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionEntity> listAll() {
        return permissionMapper.selectList(null);
    }

    @Override
    public List<PermissionEntity> listPermissionByRoleId(Long roleId) {
        return permissionMapper.findByRoleId(roleId);
    }
}
