package com.decade.electricityprediction.security;

import com.decade.electricityprediction.persistence.entity.PermissionEntity;
import com.decade.electricityprediction.persistence.entity.RoleEntity;
import com.decade.electricityprediction.persistence.entity.UserEntity;
import com.decade.electricityprediction.persistence.mapper.PermissionMapper;
import com.decade.electricityprediction.persistence.mapper.RoleMapper;
import com.decade.electricityprediction.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<RoleEntity> roleEntityList = roleMapper.findRoleIdByUserId(user.getId());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (RoleEntity roleEntity :
                roleEntityList) {
            authorityList.add(new SimpleGrantedAuthority(roleEntity.getRoleName()));
        }
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUsername(user.getUserName());
        myUserDetails.setPassword(user.getUserPassword());
        myUserDetails.setPermissionList(authorityList);
        return myUserDetails;
    }

}
