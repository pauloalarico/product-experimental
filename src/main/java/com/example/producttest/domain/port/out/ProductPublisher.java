package com.example.producttest.domain.port.out;

import com.example.producttest.domain.event.StockReserved;

public interface ProductPublisher {
    void publish(StockReserved stockReserved);
    void publishToDeadLetter(StockReserved reserved);
}
