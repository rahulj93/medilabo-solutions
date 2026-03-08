package com.medilabosolutions.gatewayservice.util;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 1000 * 60 * 10; // 10 minutes

    public String generateToken(Authentication authentication) {

        String roles = authentication.getAuthorities()
            .stream()
            .map(a -> a.getAuthority())
            .collect(Collectors.joining(","));

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim("roles", roles)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(SIGNING_KEY)
            .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token);
            return true;

        } catch (ExpiredJwtException | MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {

            System.out.println("JWT validation error: " + e.getMessage());
            return false;
        }
    }

    public List<String> getRoles(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(SIGNING_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();

        String roles = claims.get("roles", String.class);
        return Arrays.asList(roles.split(","));
    }
}