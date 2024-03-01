package com.work.szs.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    public static Map<String, String> createToken(String userId, String key, long expireTimeMs) {
        String token = Jwts.builder()
                .setClaims(Jwts.claims().setSubject(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();

        return Map.of("accessToken", token);
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public static String getUserId(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
