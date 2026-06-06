package com.example.producttest.domain.command;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public record ConsultProductCommand(
        UUID correlationId,
        UUID orderId,
        Map<UUID, Integer> products
) {
    public ConsultProductCommand {
        Objects.requireNonNull(correlationId);
        Objects.requireNonNull(orderId);
        Objects.requireNonNull(products);
    }
}
