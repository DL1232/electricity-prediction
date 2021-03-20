package com.decade.electricityprediction.persistence.mapper;

import com.decade.electricityprediction.ElectricityPredictionApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = ElectricityPredictionApplication.class)
public class RoleMapperTest {

    @Autowired
    public RoleMapper roleMapper;

    @Test
    public void findByRoleIdTest() {
        Long roleIdByUserId = roleMapper.findRoleIdByUserId(2L);
        log.info("role id:{}", roleIdByUserId);
    }

}
