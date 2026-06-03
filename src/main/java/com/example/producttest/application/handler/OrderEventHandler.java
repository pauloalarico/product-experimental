package com.example.producttest.application.handler;

import com.example.producttest.application.mapper.OrderEventMapper;
import com.example.producttest.application.usecase.VerifyStockUseCase;
import com.example.producttest.infra.messaging.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final VerifyStockUseCase verifyStock;

    private final OrderEventMapper mapper;

    public OrderEventHandler(VerifyStockUseCase verifyStock, OrderEventMapper mapper) {
        this.verifyStock = verifyStock;
        this.mapper = mapper;
    }

    public void handle(OrderCreatedEvent event) {
        var command = mapper.toCommand(event);
        verifyStock.verify(command);
    }

}
