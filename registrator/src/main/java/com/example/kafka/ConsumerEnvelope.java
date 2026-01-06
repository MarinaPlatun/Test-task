package com.example.kafka;

import com.example.model.EventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Класс envelope для приема сообщений из кафки.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ConsumerEnvelope {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("status")
  private String status;

  @JsonProperty("eventType")
  private EventType eventType;

  @JsonProperty("creator")
  private String creator;

  @JsonProperty("createdBy")
  private Date createdBy;

  public ConsumerEnvelope(Long id, String status, EventType eventType, String creator,
                          Date createdBy) {
    this.id = id;
    this.status = status;
    this.eventType = eventType;
    this.creator = creator;
    this.createdBy = createdBy;
  }
}
