package com.decade.electricityprediction.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class SecurityConfigTest {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest() {
        // 加密测试
        String decade = passwordEncoder.encode("decade");
        log.info("\"decade\"的加密结果:{}", decade);
    }

    @Test
    public void matchTest() {
        String decade = "$2a$10$M2T4op5UOMgZwgV/58hzPe0pT2IiWv0dMfwc7wKklhNcNyXahb/2W";
        boolean flag = passwordEncoder.matches("decade", decade);
        log.info("密码匹配结果:{}", flag);
    }

}
