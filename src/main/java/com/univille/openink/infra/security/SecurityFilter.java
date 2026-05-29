package com.univille.openink.infra.security;

import com.univille.openink.domain.user.User;
import com.univille.openink.domain.user.UserRepository;
import com.univille.openink.infra.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = recoverToken(request);
        if (tokenJWT != null){
            String subject = tokenService.validateToken(tokenJWT);
            User user = userRepository.findByName(subject)
                    .orElseThrow(() -> new UnauthorizedException("Usuário não encontrado"));

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    List.of()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader!=null) return authorizationHeader.replace("Bearer ", "");
        return null;
    }
}
