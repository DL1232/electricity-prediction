package com.decade.electricityprediction.security;

import com.decade.electricityprediction.ElectricityPredictionApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest(classes = ElectricityPredictionApplication.class)
public class MyUserDetailsServiceTest {

    @Autowired
    public MyUserDetailsService myUserDetailsService;

    @Test
    public void loadByUsernameTest() {
        UserDetails admin = myUserDetailsService.loadUserByUsername("admin");
        System.out.println(admin);
    }
}
