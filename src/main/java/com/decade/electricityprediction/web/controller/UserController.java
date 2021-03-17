package com.decade.electricityprediction.web.controller;

import com.decade.electricityprediction.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    // 用于返回视图
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        userDto.setEmail("H@123");
        model.addAttribute("user", userDto);
        return "register";
    }

    // 用于处理注册请求
    @PostMapping("/register")
    @ResponseBody
    public UserDto registerUserAccount(UserDto userDto) {
        System.out.println(userDto);
        return userDto;
    }

    // 用于返回视图
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // 处理登录请求
    @PostMapping("/login")
    @ResponseBody
    public String login() {
        System.out.println("login——POST");
        return "Hello";
    }
}
