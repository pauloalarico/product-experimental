package com.example.producttest.infra.messaging.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProductProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${app.kafka.producer.ack-mode}")
    private String ackConfig;

    @Value("${app.kafka.producer.type-mapping}")
    private String typeMapping;

    @Value("${app.kafka.producer.topic-producer}")
    private String topicName;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> factory = new HashMap<>();
        factory.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        factory.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        factory.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        factory.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
        factory.put(ProducerConfig.ACKS_CONFIG, ackConfig);
        factory.put(JacksonJsonSerializer.TYPE_MAPPINGS, "");
        return new DefaultKafkaProducerFactory<>(factory);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName)
                .partitions(3)
                .replicas(1)
                .build();
    }

}
