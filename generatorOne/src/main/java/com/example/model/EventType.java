package com.example.model;

/**
 * Типы событий сервиса генератора один.
 */
public enum EventType {

  SUN("SUN"),
  RAIN("RAIN");

  private final String name;

  EventType(String name) {
    this.name = name;
  }
}
