package org.globe42.web.security;

import static org.assertj.core.api.Assertions.assertThat;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for JwtHelper
 * @author JB Nizet
 */
public class JwtHelperTest {
    private JwtHelper jwtHelper;

    @Before
    public void prepare() {
        jwtHelper = new JwtHelper();
    }

    @Test
    public void shouldBuildAndParseToken() {
        // given a user
        Long userId = 42L;

        // when building a token
        String token = jwtHelper.buildToken(userId);

        // then we should have a token
        assertThat(token.length()).isGreaterThan(20);

        Claims claims = jwtHelper.extractClaims(token);
        Long extractedUserId = Long.parseLong(claims.getSubject());
        assertThat(extractedUserId).isEqualTo(userId);
    }
}
