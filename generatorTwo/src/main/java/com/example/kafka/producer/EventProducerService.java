package com.example.kafka.producer;

import static com.example.model.StatusType.NEW;

import com.example.model.Event;
import com.example.model.EventType;
import com.example.repository.EventRepository;
import java.util.Date;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Сервис по отправке сообщений регистратору о том, что событие создано, кто (какой сервис) является
 * создателем;
 */
@Service
@Slf4j
public class EventProducerService {

  @Autowired
  private KafkaTemplate<String, Event> kafkaTemplate;
  @Autowired
  private EventRepository eventRepository;

  @Value("${spring.application.name}")
  private String serviceName;

  @Scheduled(fixedRate = 4000) // Каждые 4 секунды
  public void generateAndPublishEvent() {
    Event event = new Event(NEW, getTypeEvent(), serviceName, new Date());
    Event saveEvent = eventRepository.save(event);
    kafkaTemplate.send("event-generator-two", String.valueOf(saveEvent.getId()), event);
    log.info("Service {} generate and send message about events '{}'", serviceName, event);
  }

  private EventType getTypeEvent() {
    Random rand = new Random();
    int randomNumber = rand.nextInt(3);
    return randomNumber == 1 ? EventType.SNOW : EventType.FOG;
  }
}
