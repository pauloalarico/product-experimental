package com.example.producttest.domain.port.out;

import com.example.producttest.domain.model.Product;

import java.util.UUID;

public interface ProductPersist {
    void save(Product p);
    Product findById(Product p);
    Product findById(UUID id);
}
