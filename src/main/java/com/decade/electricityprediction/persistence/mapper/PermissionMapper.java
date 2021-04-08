package com.decade.electricityprediction.persistence.mapper;

import com.decade.electricityprediction.persistence.entity.PermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lidongjie
 * @since 2021-03-19
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntity> {

    List<PermissionEntity> findByRoleId(Long roleId);

}
