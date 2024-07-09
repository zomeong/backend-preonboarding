package com.hanghae99.preonboardingbackend.jwt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.hanghae99.preonboardingbackend.config.jwt.TokenProvider;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class TokenProviderTest {

    @InjectMocks
    private TokenProvider tokenProvider;

    @Value("${jwt.secret}")
    private String secret;

    private String invalidToken = "invalidToken";
    String username = "testUser";
    Set<String> authorities = new HashSet<>();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(tokenProvider, "secret", secret);
        tokenProvider.afterPropertiesSet();
        authorities.add("ROLE_USER");
    }

    @Test
    public void testCreateAccessToken() {
        //when
        String token = tokenProvider.createAccessToken(username, authorities);
        Set<String> extractedAuthorities = tokenProvider.getAuthoritiesFromToken(token);

        //then
        assertNotNull(token);
        assertTrue(tokenProvider.validateToken(token));
        assertEquals(username, tokenProvider.getUsernameFromToken(token));
        assertThat(extractedAuthorities).containsExactlyInAnyOrderElementsOf(authorities);
    }

    @Test
    public void testCreateRefreshToken() {
        //when
        String token = tokenProvider.createRefreshToken();

        //then
        assertThat(token).isNotNull();
        assertTrue(tokenProvider.validateToken(token));
    }

    @Test
    public void testTokenExpiration() throws InterruptedException {
        //given
        ReflectionTestUtils.setField(tokenProvider, "accessTokenValidity", 1000);

        //when
        String token = tokenProvider.createAccessToken(username, authorities);
        Thread.sleep(1500);

        //then
        assertFalse(tokenProvider.validateToken(token));
    }

    @Test
    public void testValidateToken_InvalidToken() {
        //when - then
        assertFalse(tokenProvider.validateToken(invalidToken));
    }

    @Test
    public void getUsernameFromToken_InvalidToken() {
        //when-then
        assertThrows(io.jsonwebtoken.JwtException.class, () -> {
            tokenProvider.getUsernameFromToken(invalidToken);
        });
    }
}
