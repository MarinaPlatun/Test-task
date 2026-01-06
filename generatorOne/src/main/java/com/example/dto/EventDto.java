package com.example.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Дто для возврата информации о событиях.
 */
@Setter
@Getter
public class EventDto {

  private Long allEvents;
  private Long newEvents;
  private Long confirmedEvents;

}
