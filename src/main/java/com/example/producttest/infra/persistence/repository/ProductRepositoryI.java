package com.example.producttest.infra.persistence.repository;

import com.example.producttest.domain.model.Product;
import com.example.producttest.domain.port.out.ProductPersist;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductRepositoryI implements ProductPersist {

    private final ProductRepository repository;

    public ProductRepositoryI(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Product p) {
        repository.save(p);
    }

    @Override
    public Product findById(Product p) {
        return repository.findById(p.getId()).orElseThrow(() -> new RuntimeException("Product non-existent"));
    }

    @Override
    public Product findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product non-existent"));
    }

}
