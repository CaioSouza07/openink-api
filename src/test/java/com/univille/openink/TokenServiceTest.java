package com.univille.openink;

import com.univille.openink.domain.user.User;
import com.univille.openink.infra.exception.TokenJWTException;
import com.univille.openink.infra.security.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Test
    void generateAndValidateToken_shouldReturnSubject() {
        TokenService tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", "test-secret-1234567890123456");

        User user = new User();
        user.setName("usuario-teste");

        String token = tokenService.generateToken(user);
        assertNotNull(token);

        String subject = tokenService.validateToken(token);
        assertEquals(user.getName(), subject);
    }

    @Test
    void validateToken_whenInvalid_throwsTokenJWTException() {
        TokenService tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", "test-secret-1234567890123456");

        assertThrows(TokenJWTException.class, () -> tokenService.validateToken("invalid.token.value"));
    }
}
