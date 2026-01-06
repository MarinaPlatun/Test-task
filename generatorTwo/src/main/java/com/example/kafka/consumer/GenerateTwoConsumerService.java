package com.example.kafka.consumer;

import static com.example.model.StatusType.CONFIRMED;

import com.example.model.Event;
import com.example.repository.EventRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

/**
 * Прием сообщений из кафки от сервиса регистратора.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@KafkaListener(
    id = GenerateTwoConsumerService.GENERATOR_LISTENER_ID,
    topics = GenerateTwoConsumerService.IN_TOPIC,
    groupId = GenerateTwoConsumerService.GROUP_ID
)
public class GenerateTwoConsumerService {

  public static final String GENERATOR_LISTENER_ID = "generatorTwoListenerId";
  public static final String IN_TOPIC = "${app.kafka.consumer.topic-reply-registrator}";
  public static final String GROUP_ID = "${spring.kafka.consumer.group-id}";

  private final EventRepository eventRepository;
  private final ObjectMapper objectMapper;

  @Value("${spring.application.name}")
  private String serviceName;

  @KafkaHandler
  public void consumeData(@Payload String message) {
    log.info("Service {} consumed {} from stream '{}'", serviceName, message, IN_TOPIC);
    List<Long> envelope = convert(message);
    if (!CollectionUtils.isEmpty(envelope)) {
      List<Event> existEvents = eventRepository.findAllById(envelope);
      if (!CollectionUtils.isEmpty(existEvents)) {
        existEvents.forEach(event -> event.setStatus(CONFIRMED));
        eventRepository.saveAll(existEvents);
      }
    }
  }

  private List<Long> convert(String message) {
    try {
      return objectMapper.readValue(message, new TypeReference<>() {
      });
    } catch (Exception ex) {
      log.error("Failed to deserialize envelop for message:\n{}", message);
      throw new RuntimeException(ex);
    }
  }
}
