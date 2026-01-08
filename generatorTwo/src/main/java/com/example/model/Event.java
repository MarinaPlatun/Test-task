package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Entity для сервиса генерации событий.
 */
@Entity
@Table(name = "generator_two")
@Getter
@Setter
@RequiredArgsConstructor
public class Event implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusType status;

  @Column(name = "eventType")
  @Enumerated(EnumType.STRING)
  private EventType eventType;

  @Column(name = "creator")
  private String creator;

  @Column(name = "created_by")
  private Date createdBy;

  public Event(StatusType status, EventType eventType, String creator, Date createdBy) {
    this.status = status;
    this.eventType = eventType;
    this.creator = creator;
    this.createdBy = createdBy;
  }

  @Override
  public String toString() {
    return "Event{" +
        "id=" + id +
        ", status=" + status +
        ", eventType=" + eventType +
        ", creator='" + creator + '\'' +
        ", createdBy=" + createdBy +
        '}';
  }
}
