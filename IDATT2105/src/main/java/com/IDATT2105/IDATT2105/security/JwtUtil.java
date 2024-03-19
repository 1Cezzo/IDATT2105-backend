package com.IDATT2105.IDATT2105.security;

import java.util.Date;
import io.jsonwebtoken.*;

public class JwtUtil {
    private static final long EXPIRATION_TIME = 300_000;

    public static String generateToken(String username, String secret) {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public static String extractUsername(String token, String secret) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public static boolean isTokenExpired(String token) {
        return Jwts.parser()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration()
            .before(new Date());
    }

    public static String extractUsername (String token) {
        return Jwts.parser()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
