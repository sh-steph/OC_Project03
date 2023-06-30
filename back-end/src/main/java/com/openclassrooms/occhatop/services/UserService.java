package com.openclassrooms.occhatop.services;

import com.openclassrooms.occhatop.dto.UserDTO;
import com.openclassrooms.occhatop.exceptions.UserIdNotFoundException;
import com.openclassrooms.occhatop.exceptions.UserNotFoundException;
import com.openclassrooms.occhatop.models.authentication.User;
import com.openclassrooms.occhatop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
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

    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new UserNotFoundException("User with this Mail not found"));
        return getUserDTO(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundException(id));
    }

    private UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setCreated_at(user.getCreatedAt());
        userDTO.setUpdated_at(user.getUpdatedAt());
        return userDTO;
    }
}

