package com.example.producttest.infra.persistence.repository;

import com.example.producttest.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>{
    @Query(value = "SELECT * FROM td_products t WHERE t.cd_identification = uuid", nativeQuery = true)
    Optional<Product> findById(UUID uuid);
}
