package com.example.producttest.domain.port.out;

import com.example.producttest.domain.model.Product;

public interface ProductPersist {
    void save(Product p);
    Product findById(Product p);
}
