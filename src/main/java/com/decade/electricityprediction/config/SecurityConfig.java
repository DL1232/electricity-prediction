package com.decade.electricityprediction.config;

import com.decade.electricityprediction.security.JWTAuthorizationFilter;
import com.decade.electricityprediction.security.MyUserDetailsService;
import com.decade.electricityprediction.util.JsonUtil;
import com.decade.electricityprediction.util.JwtTokenUtil;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 认证相关配置
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.GET,
                // 放行 swagger ui
                // 这里放行不会经过 spring security
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
        // 禁用 csrf 跨站请求伪造
        http.csrf().disable();
        // 不需要 进行 权限验证 的URL
        http.authorizeRequests()
                // 注册与登录请求均放行
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/login").permitAll()
                // 其余请求均需要认证
                .anyRequest().authenticated();
        http.formLogin().disable();
        http.formLogin()
                // 处理登录请求 默认 POST 请求
                .loginProcessingUrl("/user/login")
                // 登录成功 处理逻辑
                .successHandler(authenticationSuccessHandler())
                // 登录失败 处理逻辑
                .failureHandler(authenticationFailureHandler());

        http.logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(logoutSuccessHandler());

        // 添加JWT鉴权拦截器
        http.addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                // 设置Session的创建策略为：Spring Security永不创建HttpSession
                // 不使用HttpSession来获取SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 访问被拒绝 没有权限时的处理
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
    }

    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            // 从User中获取权限信息
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            // 创建Token
            String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
            // 设置编码 防止乱码问题
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            // 在请求头里返回创建成功的token
            // 设置请求头为带有"Bearer "前缀的token字符串
            response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
            // 处理编码方式 防止中文乱码
            response.setContentType("text/json;charset=utf-8");
            ReturnVo<String> success = ReturnVo.success(token);
            // 将反馈塞到HttpServletResponse中返回给前台
            response.getWriter().write(JsonUtil.toJsonString(success));
        };
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            System.out.println("认证失败逻辑");
        };
    }

    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            System.out.println("自定义登出");
        };
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            System.out.println("没有权限");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
