package com.example.producttest.infra.messaging.publisher;

import com.example.producttest.domain.event.StockReserved;
import com.example.producttest.domain.port.out.ProductPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements ProductPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.producer.topic-producer}")
    private String topic;

    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(StockReserved stockReserved) {
        kafkaTemplate.send(topic, stockReserved.correlationId().toString(), stockReserved);
    }
}
