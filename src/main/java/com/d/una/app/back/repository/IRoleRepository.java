package com.d.una.app.back.repository;

import com.d.una.app.back.model.Role;
import com.d.una.app.back.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * A repository interface for Role entities.
 * Extends JpaRepository to utilize built-in methods for CRUD operations.
 */
public interface IRoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(final RoleEnum name);
    Optional<Role> findByName(final RoleEnum name);
}
