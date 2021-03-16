package com.decade.electricityprediction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ElectricityPredictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricityPredictionApplication.class, args);
    }

}
