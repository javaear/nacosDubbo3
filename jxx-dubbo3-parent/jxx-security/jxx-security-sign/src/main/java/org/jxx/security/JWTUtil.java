package org.jxx.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;

/**
 * description: JWTUtil
 * date: 2023/6/17
 * author: javaear
 */
public class JWTUtil {
    // 密钥
    private final static String secretKey = "Lucas";

    // 过期时间设置为5min
    private final static Duration expiration = Duration.ofMinutes(10);

    /**
     * 生成Token
     * @param user
     * @return
     */
    public static String generate(User user) {
        // 过期时间
        Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());
        try {
            return Jwts.builder()
                    .setSubject(new ObjectMapper().writeValueAsString(user))       // 将username放进JWT
                    .setIssuedAt(new Date())    // 设置jwt签发时间
                    .setExpiration(expiryDate)  // 设置jwt过期时间
                    .signWith(SignatureAlgorithm.HS512, secretKey)  // 设置加密算法和密钥
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析Token
     * @param token
     * @return
     */
    public static Claims parse(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            return null;
        }
    }

}
