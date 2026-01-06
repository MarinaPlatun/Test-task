package com.example.kafka;

import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;
import tools.jackson.databind.ObjectMapper;

/**
 * Класс сериализатор для отправки сообщений в кафку.
 */
public class BaseDtoSerializer implements Serializer<Object> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  @Override
  public byte[] serialize(String s, Object o) {
    return objectMapper.writeValueAsString(o).getBytes();
  }
}
