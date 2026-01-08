package com.example.service.impl;

import com.example.dto.ActionResponse;
import com.example.dto.EventDto;
import com.example.model.StatusType;
import com.example.repository.EventRepository;
import com.example.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки входящих запросов на контроллер.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

  @Value("${spring.application.name}")
  private String serviceName;

  private final EventRepository eventRepository;

  @Override
  public ActionResponse getAllEvents() {
    log.info("Service {} return all events in status new and confirmed", serviceName);
    ActionResponse actionResponse = new ActionResponse();
    EventDto eventDto = new EventDto();
    Long allEvents = eventRepository.count();
    Long newEvents = eventRepository.countByStatus(StatusType.NEW);
    Long confirmedEvents = eventRepository.countByStatus(StatusType.CONFIRMED);
    eventDto.setAllEvents(allEvents);
    eventDto.setNewEvents(newEvents);
    eventDto.setConfirmedEvents(confirmedEvents);
    actionResponse.setData(eventDto);
    return actionResponse;
  }
}
