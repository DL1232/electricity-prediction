package com.decade.electricityprediction;

import com.decade.electricityprediction.persistence.entity.User;
import com.decade.electricityprediction.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ElectricityPredictionApplicationTests {

    @Autowired
    public UserRepository userRepository;

    @Test
    void contextLoads() {
        List<User> all = userRepository.findAll();
        System.out.println(all);
    }

}
