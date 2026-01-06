package com.example.dto;

import com.example.model.EventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс дто для возврата информации о событиях.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@NoArgsConstructor
public class RegistrationEventDto {

  private Long id;
  private Long idEvent;
  private EventType eventType; // тип события
  private String creator; // источник события
  private Date createdBy;
  private Date createdByEvent;

  public RegistrationEventDto(Long id, Long idEvent, EventType eventType, String creator,
                              Date createdBy, Date createdByEvent) {
    this.id = id;
    this.idEvent = idEvent;
    this.eventType = eventType;
    this.creator = creator;
    this.createdBy = createdBy;
    this.createdByEvent = createdByEvent;
  }
}
