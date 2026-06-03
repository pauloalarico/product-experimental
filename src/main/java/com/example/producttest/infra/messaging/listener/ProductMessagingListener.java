package com.example.producttest.infra.messaging.listener;

import com.example.producttest.application.handler.OrderEventHandler;
import com.example.producttest.infra.messaging.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductMessagingListener {

    private final OrderEventHandler handler;

    public ProductMessagingListener(OrderEventHandler handler) {
        this.handler = handler;
    }

    @KafkaListener(topics = "${app.kafka.listener.order-topic}")
    public void onOrderCreated(@Payload OrderCreatedEvent event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               Acknowledgment ack) {
        log.info("Received event on topic {}, correlationId {}", topic, event.correlationId());
        handler.handle(event);
        ack.acknowledge();
    }

}
