package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Entity для сервиса регистрации.
 */
@Entity
@Table(name = "registration")
@Getter
@Setter
@RequiredArgsConstructor
public class RegistrationEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "idEvent")
  private Long idEvent;

  @Column(name = "eventType")
  @Enumerated(EnumType.STRING)
  private EventType eventType; // тип события

  @Column(name = "creator")
  private String creator; // источник события

  @Column(name = "created_by")
  private Date createdBy;

  @Column(name = "created_by_event")
  private Date createdByEvent;

  public RegistrationEvent(Long idEvent, EventType eventType, String creator, Date createdBy,
                           Date createdByEvent) {
    this.idEvent = idEvent;
    this.eventType = eventType;
    this.creator = creator;
    this.createdBy = createdBy;
    this.createdByEvent = createdByEvent;
  }
}
