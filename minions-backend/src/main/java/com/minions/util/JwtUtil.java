package com.minions.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final long TOKEN_EXPIRATION = 365L * 24 * 60 * 60 * 1000;
    private static final Key KEY = Keys.hmacShaKeyFor("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz".getBytes());

    /**
     * 根据用户id和用户名生成令牌
     *
     * @param userId
     * @param username
     * @return
     */
    public static String createToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject("P2P-USER")
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .claim("userId", userId)
                .claim("userName", username)
                .signWith(KEY)
                .compact();
    }

    /**
     * 根据token提取用户ID
     *
     * @param token
     */
    public static Long getUserId(String token) {
        try {
            if (token == null) {
                return null;
            }
            var claimsJws = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            var claims = claimsJws.getBody();
            var userId = claims.get("userId").toString();
            return Long.parseLong(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token提取用户名
     *
     * @param token
     */
    public static String getUserName(String token) {
        try {
            if (token == null) {
                return null;
            }
            var claimsJws = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            var claims = claimsJws.getBody();
            return claims.get("userName").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        String token = JwtUtil.createToken(1L, "admin");
        System.out.println(token);
        System.out.println(JwtUtil.getUserId(token));
        System.out.println(JwtUtil.getUserName(token));
    }
}
