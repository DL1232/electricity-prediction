package com.decade.electricityprediction.web.dto;


//import com.decade.electricityprediction.web.validation.PasswordMatches;

import com.decade.electricityprediction.web.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
//@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;

    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

}
