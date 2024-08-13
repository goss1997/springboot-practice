package com.example.jwt_secure_login.repository;

import com.example.jwt_secure_login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findUserByEmailAndProvider(String email, String provider);

}
