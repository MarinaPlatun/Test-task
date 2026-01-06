package com.example.dto;

import com.example.model.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс для определения параметров для фильтрации (по датам, по типу, по источку).
 */
@Getter
@Setter
public class FilterDataDto {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date dateFrom;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date dateTo;
  private EventType eventType;
  private String creator;

}
