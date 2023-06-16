package com.openclassrooms.occhatop.controllers;

import com.openclassrooms.occhatop.dao.AuthenticationRequest;
import com.openclassrooms.occhatop.dao.AuthenticationResponse;
import com.openclassrooms.occhatop.dao.RegisterRequest;
import com.openclassrooms.occhatop.dto.UserDTO;
import com.openclassrooms.occhatop.services.AuthenticationService;
import com.openclassrooms.occhatop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Controller
@RequestMapping("/api/auth") // path de base
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        log.info("New user registration : {}", request);
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("User authentication : {}", request.getEmail());
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/me")
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }
}
