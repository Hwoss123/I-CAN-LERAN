package com.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final byte[] signKey = "MBTI_SignKey$gduf_java_tribe_find me".getBytes();
    private static final Long expire = 604800000L;//7天过期

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载payload中存储的内容
     * @return jwt令牌
     */
    public static String generateJwt(Map<String,Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }


    /**
     * 解析JWT令牌
     * @param jwt jwt令牌
     * @return JWT第二部分负载payload中存储的内容
     */
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }



}