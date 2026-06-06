package com.example.producttest.application.mapper;

import com.example.producttest.domain.command.ConsultProductCommand;
import com.example.producttest.infra.messaging.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class OrderEventMapper {

    public ConsultProductCommand toCommand(OrderCreatedEvent event) {
        HashMap<UUID, Integer> products = new HashMap<>();
        for (OrderCreatedEvent.Item i : event.items()) {
            products.put(i.id(), i.quantity());
        }
        return new ConsultProductCommand(event.correlationId(), event.id(), products);
    }

}