package com.decade.electricityprediction.security;

import com.decade.electricityprediction.util.JsonUtil;
import com.decade.electricityprediction.util.JwtTokenUtil;
import com.decade.electricityprediction.util.ReturnCode;
import com.decade.electricityprediction.util.ReturnVo;
import com.decade.electricityprediction.web.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * 在 Spring Security 校验登录前
 * 提前往 Security Context 中写入用户信息
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 从请求中读取用户名 密码
        UserDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        // 从User中获取权限信息
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 创建 Token
        String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
        // 设置编码 防止乱码问题
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        // 在响应头里返回创建成功的token
        // 设置响应头为带有"Bearer "前缀的token字符串
        response.setHeader(JwtTokenUtil.TOKEN_HEADER, token);
        ReturnVo<String> success = ReturnVo.success(token);
        // 将反馈塞到HttpServletResponse中返回给前台
        PrintWriter writer = response.getWriter();
        writer.write(JsonUtil.toJsonString(success));
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        ReturnVo<Object> failure = ReturnVo.failure(ReturnCode.LOGIN_FAILURE);
        writer.write(JsonUtil.toJsonString(failure));
        writer.flush();
        writer.close();
    }
}
