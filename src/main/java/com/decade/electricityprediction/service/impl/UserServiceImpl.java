package com.decade.electricityprediction.service.impl;

import com.decade.electricityprediction.persistence.entity.UserEntity;
import com.decade.electricityprediction.persistence.mapper.UserMapper;
import com.decade.electricityprediction.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public List<UserEntity> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public UserEntity findById(Integer id) {
        return userMapper.selectById(id);
    }
}
