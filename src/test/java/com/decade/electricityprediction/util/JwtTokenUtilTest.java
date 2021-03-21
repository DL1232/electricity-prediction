package com.decade.electricityprediction.util;

import org.junit.jupiter.api.Test;

public class JwtTokenUtilTest {

    @Test
    public void createTokenTest() {
        String token = JwtTokenUtil.createToken("admin", "admin");
        System.out.println(token);
    }

    @Test
    public void getUsernameTest() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJleHAiOjE2MTY5MDI2MjYsImlhdCI6MTYxNjI5NzgyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.JamcDRKt2YhHigw0HCcDXpF4Q-ssuYr4UxbmeSaV5Eo";
        String username = JwtTokenUtil.getUsername(token);
        System.out.println(username);
    }

    @Test
    public void getUserRoleTest() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJleHAiOjE2MTY5MDI2MjYsImlhdCI6MTYxNjI5NzgyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.JamcDRKt2YhHigw0HCcDXpF4Q-ssuYr4UxbmeSaV5Eo";
        String userRole = JwtTokenUtil.getUserRole(token);
        System.out.println(userRole);
    }

    @Test
    public void isExpirationTest() {
        String token = "Bearer e yJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJleHAiOjE2MTY5MDI2MjYsImlhdCI6MTYxNjI5NzgyNiwidXNlcm5hbWUiOiJhZG1pbiJ9.JamcDRKt2YhHigw0HCcDXpF4Q-ssuYr4UxbmeSaV5Eo";
        boolean expiration = JwtTokenUtil.isExpiration(token);
        System.out.println(expiration);
    }

}
