package com.example.model;

/**
 * Типы событий сервиса генератора два.
 */
public enum EventType {

  SNOW("SNOW"),
  FOG("FOG");

  private final String name;

  EventType(String name) {
    this.name = name;
  }
}
