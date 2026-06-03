package com.example.producttest.domain.command;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public record ConsultProductCommand(
        Map<UUID, Integer> products
) {
    public ConsultProductCommand {
        Objects.requireNonNull(products);
    }
}
