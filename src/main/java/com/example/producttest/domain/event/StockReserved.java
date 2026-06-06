package com.example.producttest.domain.event;

import java.util.List;
import java.util.UUID;

public record StockReserved(UUID orderId, UUID correlationId, List<ReservedItem> reservedItems, StockStatus status) {

    public enum StockStatus {
        RESERVED,
        OUT_OF_STOCK
    }
}
