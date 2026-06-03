package com.example.producttest.infra.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID id,
        UUID correlationId,
        List<Item> items,
        BigDecimal total,
        LocalDateTime createdAt
) {
    public record Item(UUID id, Integer quantity) {
    }
}


