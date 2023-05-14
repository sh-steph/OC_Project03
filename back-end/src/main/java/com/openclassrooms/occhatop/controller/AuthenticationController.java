package com.openclassrooms.occhatop.controller;

import com.openclassrooms.occhatop.model.auth.User;
import com.openclassrooms.occhatop.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth") // path de base
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register") // path de register
    public ResponseEntity<Void> processRegister(@RequestBody User user) {
        user.setId(user.getId());
        user.setEmail(user.getEmail());
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        user.setCreatedAt(user.getCreatedAt());
        user.setUpdatedAt(user.getUpdatedAt());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}