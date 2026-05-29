package com.univille.openink.infra.security;

import com.univille.openink.infra.exception.SecurityConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        try {
            return httpSecurity.csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> {
                        authorize.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                        authorize.anyRequest().authenticated();
                    })
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        } catch (Exception e) {
            throw new SecurityConfigurationException("Erro interno na configuração de seçurança");
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        try {
            return configuration.getAuthenticationManager();
        } catch (Exception e) {
            throw new SecurityConfigurationException("Erro interno na configuração de seçurança");
        }
    }

}
