package com.decade.electricityprediction.config;

import com.decade.electricityprediction.security.JwtFilter;
import com.decade.electricityprediction.security.JwtLoginFilter;
import com.decade.electricityprediction.security.MyUserDetailsService;
import com.decade.electricityprediction.util.JsonUtil;
import com.decade.electricityprediction.util.ReturnCode;
import com.decade.electricityprediction.util.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 认证相关配置
        // 使用 自定义 的从数据库查取权限
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.GET,
                // 放行 swagger ui
                // 这里放行不会经过 spring security Filter
                "/swagger-ui.html",
                "/swagger-ui/*",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/webjars/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/listAll").hasRole("Hello");
        // 以下两个函数 均会在 Spring Security 最后的 Exception Filter 中处理
        // 登录后没有权限的处理逻辑
        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            ReturnVo<Object> failure = ReturnVo.failure(ReturnCode.FORBIDDEN);
            // 设置编码 防止乱码问题
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            // 获取 writer 要在设置 ContentType 后
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.toJsonString(failure));
            writer.flush();
            writer.close();
        });
        // 未登录时的处理逻辑
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            ReturnVo<Object> failure = ReturnVo.failure(ReturnCode.FORBIDDEN);
            // 设置编码 防止乱码问题
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.toJsonString(failure));
            writer.flush();
            writer.close();
        });
        http.addFilterBefore(
                new JwtLoginFilter("/user/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
