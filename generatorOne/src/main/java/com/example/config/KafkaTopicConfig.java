package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация для создания топика для отправки события сервису регистратору.
 */
@Configuration
public class KafkaTopicConfig {

  @Value("${app.kafka.producer.topic-event-generator-one}")
  private String topicEventGeneratorOne;

  @Bean
  public NewTopic myTopic() {
    return new NewTopic(topicEventGeneratorOne, 1, (short) 1); // Имя, партиции, репликации
  }
}
