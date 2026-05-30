package com.univille.openink.controller;

import com.univille.openink.domain.user.User;
import com.univille.openink.domain.user.UserRepository;
import com.univille.openink.domain.user.dto.UserResponse;
import com.univille.openink.infra.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserRepository userRepository;
    private UserController userController;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        userController = new UserController(userRepository);
    }

    @Test
    @DisplayName("Deve retornar usuário existente")
    void getExistingUser(){
        User user = new User(1L, "teste");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<UserResponse> response = userController.get(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1L, response.getBody().id());
        assertEquals("teste", response.getBody().name());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando usuário não existir")
    void getNotFound(){
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userController.get(99L));
    }

}
