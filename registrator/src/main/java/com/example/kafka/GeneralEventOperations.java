package com.example.kafka;

import com.example.model.RegistrationEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

/**
 * Общие методы приема и обратки сообщений из сервисов генераторов.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GeneralEventOperations {

  private final ObjectMapper objectMapper;

  public List<RegistrationEvent> toEvents(List<String> kafkaMessages) {
    List<ConsumerEnvelope> list = kafkaMessages.stream()
        .map(kafkaMessage -> {
          try {
            return toEnvelope(kafkaMessage);
          } catch (Exception e) {
            log.error("Failed to process message: {}. Reason: {}", kafkaMessage, e.getMessage());
            return null;
          }
        })
        .filter(Objects::nonNull)
        .toList();

    return getListRegistrationEvent(list);
  }

  private ConsumerEnvelope toEnvelope(String kafkaMessage) {
    try {
      return objectMapper.readValue(kafkaMessage, new TypeReference<>() {
      });
    } catch (Exception ex) {
      log.error("Failed to deserialize envelop for kafkaMessage:\n{}", kafkaMessage);
      throw new RuntimeException(ex);
    }
  }

  public List<RegistrationEvent> getListRegistrationEvent(
      List<ConsumerEnvelope> consumerEnvelopes) {
    List<RegistrationEvent> events = new ArrayList<>();
    consumerEnvelopes.forEach(envelope -> events.add(
        new RegistrationEvent(envelope.getId(), envelope.getEventType(),
            envelope.getCreator(), new Date(), envelope.getCreatedBy())));
    return events;
  }
}
