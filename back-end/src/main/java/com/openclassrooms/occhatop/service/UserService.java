package com.openclassrooms.occhatop.service;

import com.openclassrooms.occhatop.model.auth.User;
import com.openclassrooms.occhatop.repository.UserRepository;
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
            .orElseThrow(() -> new UsernameNotFoundException("The following user doesn't exist : " + email));
        return new User(
                userFind.getId(),
                userFind.getEmail(),
                userFind.getName(),
                userFind.getPassword(),
                userFind.getCreatedAt(),
                userFind.getUpdatedAt());
    }
}
