package com.d.una.app.back.repository;

import com.d.una.app.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(final String email);
    boolean existsByEmail(final String email);
}
