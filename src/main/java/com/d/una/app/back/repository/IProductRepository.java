package com.d.una.app.back.repository;

import com.d.una.app.back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository interface for Customer entities.
 * Extends JpaRepository to utilize built-in methods for CRUD operations.
 */
public interface IProductRepository extends JpaRepository<Product, Long> {

}
