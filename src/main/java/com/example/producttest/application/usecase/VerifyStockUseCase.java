package com.example.producttest.application.usecase;

import com.example.producttest.domain.command.ConsultProductCommand;
import com.example.producttest.domain.port.out.ProductPersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerifyStockUseCase {

    private final ProductPersist repository;

    public VerifyStockUseCase(ProductPersist repository) {
        this.repository = repository;
    }

    public void verify(ConsultProductCommand command) {
        var productsRequested = command.products();

        productsRequested.forEach((id, quantity) -> {
            var product = repository.findById(id);
            if(product.getStockQuantity() < quantity) {
                product.reduceStock(quantity);
                log.info("Reduce stock of product {}, reduced to {}. With a total of {} in stock", product.getName(), quantity, product.getStockQuantity());
            }
            log.error("Stock not greater than order for product {}. A total of {} in stock, requested {}", product.getName(), product.getStockQuantity(), quantity);
        });

    }

}
