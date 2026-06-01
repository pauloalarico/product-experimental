package com.example.producttest.infra.persistence.repository;

import com.example.producttest.domain.model.Product;
import com.example.producttest.domain.port.out.ProductPersist;
import org.springframework.stereotype.Repository;

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

}
