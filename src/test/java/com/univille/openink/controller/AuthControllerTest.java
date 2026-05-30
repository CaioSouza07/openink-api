package com.univille.openink.controller;

import com.univille.openink.domain.user.User;
import com.univille.openink.domain.user.UserRepository;
import com.univille.openink.domain.user.dto.AuthRequest;
import com.univille.openink.infra.security.TokenResponse;
import com.univille.openink.infra.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private UserRepository userRepository;
    private TokenService tokenService;
    private AuthController authController;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        tokenService = mock(TokenService.class);
        authController = new AuthController(userRepository, tokenService);
    }

    @Test
    @DisplayName("Deve autenticar usuário existente e retornar token")
    void authExistingUser(){
        AuthRequest request = new AuthRequest("teste");
        User user = new User(1L, "teste");

        when(userRepository.findByName("teste")).thenReturn(Optional.of(user));
        when(tokenService.generateToken(user)).thenReturn("token-123");

        ResponseEntity<TokenResponse> response = authController.auth(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("token-123", response.getBody().token());
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve criar usuário quando não existir e retornar token")
    void authCreateNewUser(){
        AuthRequest request = new AuthRequest("novo");
        User saved = new User(2L, "novo");

        when(userRepository.findByName("novo")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(saved);
        when(tokenService.generateToken(saved)).thenReturn("token-456");

        ResponseEntity<TokenResponse> response = authController.auth(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("token-456", response.getBody().token());
        verify(userRepository, times(1)).save(any(User.class));
    }

}
