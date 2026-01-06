package com.example.kafka;

import com.example.model.RegistrationEvent;
import com.example.repository.RegistrationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Прием сообщений из кафки от сервиса генератора один.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@KafkaListener(
    id = GenerateOneConsumerService.REGISTRATOR_LISTENER_ID,
    topics = GenerateOneConsumerService.IN_TOPIC,
    groupId = GenerateOneConsumerService.GROUP_ID,
    batch = "true",
    containerFactory = "batchKafkaListenerContainerFactory"
)
public class GenerateOneConsumerService {

  public static final String REGISTRATOR_LISTENER_ID = "registratorOneListenerId";
  public static final String IN_TOPIC = "${app.kafka.consumer.topic-generator-one}";
  public static final String GROUP_ID = "${spring.kafka.consumer.group-id}";

  private final RegistrationRepository registrationRepository;
  private final GeneralEventOperations generalEventOperations;

  @Autowired
  private KafkaTemplate<String, List<Long>> kafkaTemplate;


  @KafkaHandler
  public void consumeData(@Payload List<String> kafkaMessages, Acknowledgment acknowledgment) {
    log.info("Registration consumed {} messages from stream '{}'",
        kafkaMessages.size(), GenerateOneConsumerService.IN_TOPIC);
    try {
      List<RegistrationEvent> events = generalEventOperations.toEvents(kafkaMessages);
      if (!CollectionUtils.isEmpty(events)) {
        registrationRepository.saveAll(events);
        acknowledgment.acknowledge();
        sendMessageToReplyTopic(events);
      }

    } catch (RetryableException retryableException) {
      log.error("A retryable exception occurred: going to retry.", retryableException.getCause());
      throw retryableException;
    }
  }

  private void sendMessageToReplyTopic(List<RegistrationEvent> events) {
    List<Long> idEventsForCreator = events.stream().map(RegistrationEvent::getIdEvent).toList();
    kafkaTemplate.send("event-reply-generator-one", idEventsForCreator);
    log.info("Registration send message with id evens '{}'", idEventsForCreator);
  }
}


