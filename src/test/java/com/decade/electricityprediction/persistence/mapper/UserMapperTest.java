package com.decade.electricityprediction.persistence.mapper;

import com.decade.electricityprediction.ElectricityPredictionApplication;
import com.decade.electricityprediction.persistence.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = ElectricityPredictionApplication.class)
public class UserMapperTest {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void insertTest() {
        UserEntity admin = userMapper.findByUsername("admin");
        log.info("user:{}", admin);
    }
}
