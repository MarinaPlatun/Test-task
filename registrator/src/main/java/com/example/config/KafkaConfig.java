package com.example.config;


import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * Конфигурация для кафки для пакетного приема сообщений.
 */
@Configuration
@EnableKafka
public class KafkaConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean("batchKafkaListenerContainerFactory")
  ConcurrentKafkaListenerContainerFactory<String, String> batchKafkaListenerContainerFactory(
      KafkaProperties properties,
      @Value("${app.kafka.consumer.max-poll-records}") int maxPollRecords,
      @Value("${app.kafka.consumer.poll-timeout}") Duration pollTimeout) {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory(properties.getConsumer(), maxPollRecords));
    factory.setConcurrency(1);
    factory.getContainerProperties().setAckMode(MANUAL);
    factory.getContainerProperties().setPollTimeout(pollTimeout.toMillis());
    return factory;
  }

  private ConsumerFactory<String, String> consumerFactory(KafkaProperties.Consumer properties,
                                                          int maxPollRecords) {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(GROUP_ID_CONFIG, properties.getGroupId());
    configProps.put(AUTO_OFFSET_RESET_CONFIG, properties.getAutoOffsetReset());
    configProps.put(MAX_POLL_RECORDS_CONFIG, maxPollRecords);
    configProps.put(KEY_DESERIALIZER_CLASS_CONFIG, properties.getKeyDeserializer());
    configProps.put(VALUE_DESERIALIZER_CLASS_CONFIG, properties.getValueDeserializer());
    configProps.put(ENABLE_AUTO_COMMIT_CONFIG, false);
    return new DefaultKafkaConsumerFactory<>(configProps);
  }
}
