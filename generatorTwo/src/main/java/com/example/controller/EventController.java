package com.example.controller;

import com.example.dto.ActionResponse;
import com.example.service.EventService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для возврата количества сгенерированных сообщений в генераторе два, сколько из них
 * обработано, а сколько нет.
 */

@RestController
@RequestMapping("/generator/two")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @GetMapping(value = "/all")
  @ApiOperation(value = "Get all events in status new and confirmed",
      notes = "return events", response = ActionResponse.class)
  public ActionResponse getAllEvents() {
    return eventService.getAllEvents();
  }

}
