package com.decade.electricityprediction.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    // Token 请求头
    public static final String TOKEN_HEADER = "Authorization";
    // Token 前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 过期时间 时间 ms
    public static final long EXPIRATION = 1000 * 60 * 60 * 24 * 7;
    // 密钥
    public static final String SECURITY_KEY = "secret";

    /**
     * 生成Token
     */
    public static String createToken(String username, String role) {
        Map<String, Object> map = new HashMap<>();
        map.put("role", role);
        // Jwt 由 header payload 以及 signature 完成
        String token = Jwts.builder()
                .setSubject(username)
                // 设置 JWT 的 payload
                // map 会转换为 json
                .setClaims(map)
                .claim("username", username)
                // JWT 签发时间
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                // 签名算法 以及秘钥
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
        return TOKEN_PREFIX + token;
    }

    public static String removeBearer(String bearerToken) {
        return bearerToken.substring(7);
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token) throws SignatureException {
        token = removeBearer(token);
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECURITY_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("username").toString();
    }

    /**
     * 从Token中获取用户角色
     */
    public static String getUserRole(String token) throws SignatureException {
        token = removeBearer(token);
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECURITY_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role").toString();
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) throws SignatureException {
        token = removeBearer(token);
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECURITY_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }

}
