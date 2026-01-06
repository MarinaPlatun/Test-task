package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Дто для возврата информации о событиях.
 */
@Getter
@Setter
@JsonInclude
@NoArgsConstructor
public class ActionResponse implements Serializable {

  private Object data;
  private boolean success = true;
  private String message;
  private String error;

}
