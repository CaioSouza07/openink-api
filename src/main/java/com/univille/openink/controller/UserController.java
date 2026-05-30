package com.univille.openink.controller;

import com.univille.openink.domain.user.User;
import com.univille.openink.domain.user.UserRepository;
import com.univille.openink.domain.user.dto.UserResponse;
import com.univille.openink.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));

        return ResponseEntity.ok(new UserResponse(user));
    }
}
