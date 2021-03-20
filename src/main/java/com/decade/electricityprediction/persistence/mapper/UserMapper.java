package com.decade.electricityprediction.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.decade.electricityprediction.persistence.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lidongjie
 * @since 2021-03-19
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity findByUsername(String username);

}
