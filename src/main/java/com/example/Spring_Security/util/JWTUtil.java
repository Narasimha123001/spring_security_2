package com.example.Spring_Security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final long EXPIRATION_TIME = 1000*60*60; //1 hour
    private final String SECRET = "my-super-secret-key-that-is- iuouydc7fp987fp983r398r934w8gf98glong-enough-Narasimah@132";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key , SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String username, UserDetails userdetails , String token) {
        //TODO -check if username is same as username in userdetails

        return username.equals(userdetails.getUsername()) && !isTokenExpired(token);
        //TODO -check if token is not expired
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }
}
