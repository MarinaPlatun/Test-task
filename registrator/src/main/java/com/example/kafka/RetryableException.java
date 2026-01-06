package com.example.kafka;

/**
 * Тип исключения, сигнализирующий о временной ошибке, которую можно исправить повторной попыткой
 * операции.
 */
public class RetryableException extends RuntimeException {

  public RetryableException(Throwable cause) {
    super(cause);
  }
}
