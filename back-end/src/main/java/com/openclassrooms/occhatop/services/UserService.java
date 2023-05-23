package com.openclassrooms.occhatop.services;

import com.openclassrooms.occhatop.models.authentication.User;
import com.openclassrooms.occhatop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userFind = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(
                userFind.getId(),
                userFind.getEmail(),
                userFind.getName(),
                userFind.getPassword(),
                userFind.getCreatedAt(),
                userFind.getUpdatedAt());
    }

    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow();
    }
}
