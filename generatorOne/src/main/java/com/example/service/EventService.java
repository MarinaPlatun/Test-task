package com.example.service;

import com.example.dto.ActionResponse;

/**
 * Интерфейс для обработки входящих запросов на контроллер.
 */
public interface EventService {

  ActionResponse getAllEvents();

}
