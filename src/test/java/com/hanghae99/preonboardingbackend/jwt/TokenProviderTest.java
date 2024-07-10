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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class TokenProviderTest {

    @InjectMocks
    private TokenProvider tokenProvider;

    private String invalidToken = "invalidToken";
    String username = "testUser";
    Set<String> authorities;

    @BeforeEach
    public void setUp() throws Exception {
        String secret = "a71b8a7edfd427da96e2460b24b6eebe7bd0a6c24aae5a3b0728e843e29f632809a717e6c0825c979def91612c625e70d821a51512fed72ff18aca638a7c4c41";
        ReflectionTestUtils.setField(tokenProvider, "secret", secret);
        tokenProvider.afterPropertiesSet();

        authorities = new HashSet<>();
        authorities.add("ROLE_USER");
    }

    @Test
    public void testCreateAccessToken_success() {
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
    public void testCreateRefreshToken_success() {
        //when
        String token = tokenProvider.createRefreshToken();

        //then
        assertThat(token).isNotNull();
        assertTrue(tokenProvider.validateToken(token));
    }

    @Test
    public void testTokenExpiration_success() throws InterruptedException {
        //given
        ReflectionTestUtils.setField(tokenProvider, "accessTokenValidity", 1000);

        //when
        String token = tokenProvider.createAccessToken(username, authorities);
        Thread.sleep(1500);

        //then
        assertFalse(tokenProvider.validateToken(token));
    }

    @Test
    public void testValidateToken_fail_invalidToken() {
        //when - then
        assertFalse(tokenProvider.validateToken(invalidToken));
    }

    @Test
    public void testGetUsernameFromToken_fail_invalidToken() {
        //when-then
        assertThrows(io.jsonwebtoken.JwtException.class, () -> {
            tokenProvider.getUsernameFromToken(invalidToken);
        });
    }
}
