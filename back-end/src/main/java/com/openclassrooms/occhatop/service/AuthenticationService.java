package com.openclassrooms.occhatop.service;

import com.openclassrooms.occhatop.dao.AuthenticationRequest;
import com.openclassrooms.occhatop.dao.AuthenticationResponse;
import com.openclassrooms.occhatop.dao.RegisterRequest;
import com.openclassrooms.occhatop.model.auth.User;
import com.openclassrooms.occhatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .bearerToken(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
//        ajouter la condition si il ne trouve pas = 401
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .bearerToken(jwtToken)
                .build();
    }
}
