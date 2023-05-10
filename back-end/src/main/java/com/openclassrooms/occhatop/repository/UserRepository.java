package com.openclassrooms.occhatop.repository;

import com.openclassrooms.occhatop.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
