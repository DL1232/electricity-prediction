package com.decade.electricityprediction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 排除 SpringSecurityAutoConfiguration
 * 使用配置类来配置 SpringSecurity
 */
@MapperScan("com.decade.electricityprediction.persistence.mapper")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ElectricityPredictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricityPredictionApplication.class, args);
    }

}
