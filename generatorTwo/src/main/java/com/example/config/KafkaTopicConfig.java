package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Конфигурация для создания топика для отправки события сервису регистратору.
 */
@Configuration
@EnableKafka
public class KafkaTopicConfig {

  @Value("${app.kafka.producer.topic-event-generator-two}")
  private String topicEventGeneratorTwo;

  @Bean
  public NewTopic myTopic() {
    return new NewTopic(topicEventGeneratorTwo, 1, (short) 1); // Имя, партиции, репликации
  }
}