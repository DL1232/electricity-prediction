package com.decade.electricityprediction.persistence.mapper;

import com.decade.electricityprediction.ElectricityPredictionApplication;
import com.decade.electricityprediction.persistence.entity.PermissionEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(classes = ElectricityPredictionApplication.class)
public class PermissionMapperTest {

    @Autowired
    public PermissionMapper permissionMapper;

    @Test
    public void findByRoleIdTest() {
        List<PermissionEntity> byRoleId = permissionMapper.findByRoleId(1L);
        log.info("permission list:{}", byRoleId);
    }

}
