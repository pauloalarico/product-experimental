package com.example.producttest.application.usecase;

import com.example.producttest.domain.command.ConsultProductCommand;
import com.example.producttest.domain.event.ReservedItem;
import com.example.producttest.domain.event.StockReserved;
import com.example.producttest.domain.port.out.ProductPersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VerifyStockUseCase {

    private final ProductPersist repository;

    public VerifyStockUseCase(ProductPersist repository) {
        this.repository = repository;
    }

    public Optional<List<ReservedItem>> verify(ConsultProductCommand command) {
        var productsRequested = command.products();
        List<ReservedItem> reservedItems = new ArrayList<>();

        for (var entry : productsRequested.entrySet()) {
            var id = entry.getKey();
            var quantity = entry.getValue();
            var product = repository.findById(id);
            if (product.getStockQuantity() < quantity) {
                log.error("Stock not greater than order for product {}. A total of {} in stock, requested {}", product.getName(), product.getStockQuantity(), quantity);
                return Optional.empty();
            }

            product.reduceStock(quantity);
            log.info("Reduce stock of product {}, reduced to {}. With a total of {} in stock", product.getName(), quantity, product.getStockQuantity());
            reservedItems.add(new ReservedItem(product.getId(), product.getName(), product.getType(),
                    product.getPrice(), quantity));

        }
        return Optional.of(reservedItems);
    }

}
