package com.decade.electricityprediction.security;

import com.decade.electricityprediction.util.JsonUtil;
import com.decade.electricityprediction.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        // 取出 Token
        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        if (tokenHeader != null) {
            String username, userRole;
            try {
                username = JwtTokenUtil.getUsername(tokenHeader);
                userRole = JwtTokenUtil.getUserRole(tokenHeader);
            } catch (Exception e) {
                // Token 有误
                chain.doFilter(req, response);
                return;
            }
            userRole = userRole.replace("[", "");
            userRole = userRole.replace("]", "");
            List<GrantedAuthority> grantedAuthorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(userRole);
            // 日志 打印 一下 恢复的 权限
            log.info("{} Token 权限是:{}", username, grantedAuthorities);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        // 没有 Token
        chain.doFilter(req, response);
    }

}
