package com.example.model;

/**
 * Типы событий.
 */
public enum EventType {

  SUN("SUN"),
  RAIN("RAIN"),
  SNOW("SNOW"),
  FOG("FOG");

  private final String name;

  EventType(String name) {
    this.name = name;
  }

}
