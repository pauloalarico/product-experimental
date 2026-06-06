package com.example.producttest.application.usecase;

import com.example.producttest.domain.command.ConsultProductCommand;
import com.example.producttest.domain.event.ReservedItem;
import com.example.producttest.domain.event.StockReserved;
import com.example.producttest.domain.port.out.ProductPersist;
import com.example.producttest.domain.port.out.ProductPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VerifyStockUseCase {

    private final ProductPersist repository;

    private final ProductPublisher publisher;

    public VerifyStockUseCase(ProductPersist repository, ProductPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
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
                publisher.publish(new StockReserved(command.correlationId(), command.orderId(), null,
                        StockReserved.StockStatus.OUT_OF_STOCK));
            }
            product.reduceStock(quantity);
            log.info("Reduce stock of product {}, reduced to {}. With a total of {} in stock", product.getName(), quantity, product.getStockQuantity());
            reservedItems.add(new ReservedItem(product.getId(), product.getName(), product.getType(),
                    product.getPrice(), quantity));
        }
        publisher.publish(new StockReserved(command.correlationId(), command.orderId(),
                reservedItems, StockReserved.StockStatus.RESERVED));
    }

}
