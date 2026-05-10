package com.example.deploytesting.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    // In a real app, move this to application.properties
    private String jwtSecret = "yourSecretKeyMustBeVeryLongAndSecureOtherwiseItWillFail";
    private int jwtExpirationMs = 86400000; // 24 hours

    private Key key() {
        // Use .getBytes() instead of Decoders.BASE64.decode
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // 1. Generate a token for a validated user
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. Extract username from a token
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // 3. Check if the token is valid (not expired, not tampered)
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            // This will print to your IDE console exactly why it failed
            System.out.println("JWT Validation Error: " + e.getMessage());
        }
        return false;
    }
}