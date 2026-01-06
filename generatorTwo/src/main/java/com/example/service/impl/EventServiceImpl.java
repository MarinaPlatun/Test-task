package com.example.service.impl;

import com.example.dto.ActionResponse;
import com.example.dto.EventDto;
import com.example.model.StatusType;
import com.example.repository.EventRepository;
import com.example.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки входящих запросов на контроллер.
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  @Override
  public ActionResponse getAllEvents() {
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
