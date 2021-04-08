package com.decade.electricityprediction.security;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class Response {

    private String token;
    private UserDetails userDetails;
}
