package com.example.producttest.domain.event;

import com.example.producttest.domain.enums.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public record ReservedItem(UUID productId, String name, ProductType type, BigDecimal price, Integer quantityReserved) {}
