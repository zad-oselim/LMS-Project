package com.spring.service;

import com.spring.model.entity.User;
import com.spring.repository.UserRepository;
import com.spring.model.dto.AuthenticationRequest;
import com.spring.model.dto.AuthenticationResponse;
import com.spring.security.jwt.JwtServices;
import com.spring.security.AppUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(new AppUserDetail(user));
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .build();
    }
}