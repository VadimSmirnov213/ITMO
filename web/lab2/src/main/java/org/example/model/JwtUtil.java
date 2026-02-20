package org.example.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;


public class JwtUtil {
    private final SecretKey secretKey;
    private final long expirationTime;


    public JwtUtil() {
        this.secretKey = Keys.hmacShaKeyFor(
            "my-super-secret-key-for-jwt-signing-at-least-32-bytes-long-enough".getBytes()
        );
        this.expirationTime = 24 * 60 * 60 * 1000; 
    }


    public JwtUtil(String secretKeyString, long expirationTimeMillis) {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
        this.expirationTime = expirationTimeMillis;
    }


    public String generateAdminToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("role", "admin")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expirationTime)))
                .signWith(secretKey)
                .compact();
    }


    public String generateUserToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("role", "user")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expirationTime)))
                .signWith(secretKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return false;
            }
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isAdmin(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return false;
            }
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            String role = claims.get("role", String.class);
            return "admin".equals(role);
        } catch (Exception e) {
            return false;
        }
    }


    public String getUsername(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return null;
            }
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }


    public String getRole(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return null;
            }
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.get("role", String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
