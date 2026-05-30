package com.univille.openink.infra.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.univille.openink.domain.user.User;
import com.univille.openink.infra.exception.TokenJWTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("openink-api")
                    .withSubject(user.getName())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new TokenJWTException("Erro ao gerar o token JWT");
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("openink-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            throw new TokenJWTException("Erro ao validar o token JWT");
        }
    }
}
