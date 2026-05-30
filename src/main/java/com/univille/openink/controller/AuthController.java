package com.univille.openink.controller;

import com.univille.openink.domain.user.User;
import com.univille.openink.domain.user.UserRepository;
import com.univille.openink.domain.user.dto.AuthRequest;
import com.univille.openink.infra.security.TokenResponse;
import com.univille.openink.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public ResponseEntity<TokenResponse> auth(@RequestBody @Valid AuthRequest request){

        User user = userRepository.findByName(request.name())
                .orElseGet(
                        () -> userRepository.save(new User(null, request.name()))
                );

        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(token));
    }

}
