package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация для создания топиков для ответного сообщения-подтверждения.
 */
@Configuration
public class KafkaTopicConfig {

  @Value("${app.kafka.producer.topic-reply-generator-one}")
  private String topicReplyGeneratorOne;

  @Value("${app.kafka.producer.topic-reply-generator-two}")
  private String topicReplyGeneratorTwo;

  @Bean
  public NewTopic replyOne() {
    return new NewTopic(topicReplyGeneratorOne, 1, (short) 1); // Имя, партиции, репликации
  }

  @Bean
  public NewTopic replyTwo() {
    return new NewTopic(topicReplyGeneratorTwo, 1, (short) 1); // Имя, партиции, репликации
  }
}