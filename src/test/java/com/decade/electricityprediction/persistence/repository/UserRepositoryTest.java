package com.decade.electricityprediction.persistence.repository;

import com.decade.electricityprediction.persistence.entity.User;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;

    @Test
    public void insertTest() {
        User user = new User();
        user.setFirstName("test");
        user.setEmail("test_email");
//        User save = userRepository.save(user);
//        System.out.println(save);
        log.error("user:{}", user);
    }
}
