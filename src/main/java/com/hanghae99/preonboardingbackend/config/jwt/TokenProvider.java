package com.hanghae99.preonboardingbackend.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    private static final String BEARER = "bearer";
    private static final String AUTHORITIES_KEY = "authorities";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_AUTHORIZATION_HEADER = "RefreshAuthorization";
    private long accessTokenValidity = 1000 * 60 * 30;
    private long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7;

    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createAccessToken(String username, Set<String> authorities) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + accessTokenValidity);

        return Jwts.builder()
            .setSubject(username)
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setIssuedAt(new Date())
            .setExpiration(validity)
            .compact();
    }

    public String createRefreshToken() {
        long now = (new Date()).getTime();
        Date validity = new Date(now + refreshTokenValidity);

        return Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS512)
            .setIssuedAt(new Date())
            .setExpiration(validity)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.trace("Invalid JWT token trace: {}", e);
        }
        return false;
    }

    public String getAccessToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    public String getRefreshToken(HttpServletRequest request) {
        return request.getHeader(REFRESH_AUTHORIZATION_HEADER);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Set<String> getAuthoritiesFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        return ((List<?>) claims.get(AUTHORITIES_KEY)).stream()
            .map(authority -> (String) authority)
            .collect(Collectors.toSet());
    }

    public String substringToken(String tokenValue) {
        if(tokenValue.startsWith(BEARER)) {
            return tokenValue.substring(BEARER.length());
        }
        throw new IllegalArgumentException("Invalid token");
    }
}